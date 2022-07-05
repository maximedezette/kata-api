package cucumber.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KataStepdefs {

    private static Response response;

    @LocalServerPort
    private int port;

    @Before
    public void setUp()  {
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

    @Given("A user publish a new Kata")
    public void aUserPublishANewKata() {
            RequestSpecification request = RestAssured.given();


        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "AperoTech");

        response = request
                    .header("Content-Type", "application/json")
                    .body(requestParams.toJSONString())
                    .post("/kata");
    }
}
