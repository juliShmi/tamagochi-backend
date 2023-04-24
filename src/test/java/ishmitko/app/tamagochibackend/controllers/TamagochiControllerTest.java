package ishmitko.app.tamagochibackend.controllers;

import ishmitko.app.tamagochibackend.dto.AnimalDTO;
import ishmitko.app.tamagochibackend.dto.AnimalNameHolder;
import ishmitko.app.tamagochibackend.util.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static ishmitko.app.tamagochibackend.TestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TamagochiController.class)
public class TamagochiControllerTest {

    private static final String PATH = "/tamagochi";

    private static final MockHttpServletRequestBuilder POST = post(PATH + "/animal");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;


    @Test
    void createAnimal() throws Exception {
        String expectedResponseBody = loadFile("/animal-created.json");

        System.out.println("expectedResponseBody: " + expectedResponseBody);

        when(animalService.createAnimal(any(AnimalNameHolder.class)))
                .thenReturn(new AnimalDTO("Meow", 1, 10.0, 10.0));

//        String actualResponseBody = mockMvc.perform(POST
//                        .content(loadFile("/animal-name.json"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();

        var response = mockMvc.perform(POST
                        .content(loadFile("/animal-name.json"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String actualResponseBody = response.getContentAsString();
        String actualAcaoHeader = response.getHeader("Access-Control-Allow-Origin");

        Assertions.assertAll(
                () -> Assertions.assertEquals("*", actualAcaoHeader),
                () -> JSONAssert.assertEquals(expectedResponseBody, actualResponseBody, JSONCompareMode.STRICT_ORDER)
        );
    }
}
