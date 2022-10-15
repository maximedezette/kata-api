package cucumber.steps;

import com.aperotech.kata.domain.Kata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KataStepDefs {

    private static Response response;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @When("A user asks for the Kata list")
    public void aUserAsksForTheKataList() {
        RequestSpecification request = RestAssured.given();

        response = request.get("/kata");
    }

    @Then("He should have a success response")
    public void heShouldHaveASuccessResponse() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Given("A user publish a new Kata named {word}")
    public void aUserPublishANewKata(String name) {
        RequestSpecification request = RestAssured.given();


        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);

        response = request
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .post("/kata");
    }

    @And("The following kata list should be in the response content")
    public void theFollowingKataShouldBeInTheResponseContent(DataTable table) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String serializedKata = response.getBody().asString();
        List<Kata> actualKatas = mapper.readValue(serializedKata, new TypeReference<>() {
        });

        List<String> expectedKataNames = table.asList().subList(1, table.height());

        for (String expectedKataName: expectedKataNames) {
           boolean nameFound = actualKatas.stream()
                    .anyMatch(kata -> kata.getName().equals(expectedKataName));
           assertThat(nameFound).isTrue();
        }


    }
}
