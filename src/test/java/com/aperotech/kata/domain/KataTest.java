package com.aperotech.kata.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KataTest {

    @Test
    void shouldBeDeserialized() throws JsonProcessingException {
        String serializedKata = "{\"name\":\"some name\"}";
        Kata expectedKata = new Kata("some name");
        ObjectMapper mapper = new ObjectMapper();

        Kata dezerializedKata = mapper.readValue(serializedKata, Kata.class);

        assertThat(dezerializedKata).isEqualTo(expectedKata);
    }

}