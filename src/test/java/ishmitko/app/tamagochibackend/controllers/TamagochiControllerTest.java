package ishmitko.app.tamagochibackend.controllers;

import ishmitko.app.tamagochibackend.dto.AnimalDTO;
import ishmitko.app.tamagochibackend.dto.AnimalNameHolder;
import ishmitko.app.tamagochibackend.exceptions.AnimalNotFoundException;
import ishmitko.app.tamagochibackend.util.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static ishmitko.app.tamagochibackend.TestUtils.*;
import static ishmitko.app.tamagochibackend.util.ErrorMessages.ANIMAL_NOT_FOUND;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TamagochiController.class)
public class TamagochiControllerTest {

    private static final String PATH = "/tamagochi/";
    private static final String UUID = "3ebaeebf-78e0-44ff-8125-3cff02095bfa";

    private static final MockHttpServletRequestBuilder POST = post(PATH + "animal");
    private static final MockHttpServletRequestBuilder GET = get(PATH + UUID);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;


    @Test
    void createAnimal() throws Exception {
        String expectedResponseBody = loadFile("/animal-created.json");
        System.out.println("expectedResponseBody: " + expectedResponseBody);

        when(animalService.createAnimal(any(AnimalNameHolder.class)))
                .thenReturn(new AnimalDTO(UUID,"Meow", 1, 10.0, 10.0));

        var response = mockMvc.perform(POST
                        .content(loadFile("/animal-name.json"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String actualResponseBody = response.getContentAsString();
        Assertions.assertAll(
                () -> assertEquals(expectedResponseBody, actualResponseBody, JSONCompareMode.STRICT_ORDER)
        );
    }

    @Test
    void getAnimal() throws Exception {
        String expectedResponseBody = loadFile("/animal-created.json");
        System.out.println("expectedResponseBody: " + expectedResponseBody);

        when(animalService.getAnimal(any(String.class)))
                .thenReturn(loadObject("/animal-created.json", AnimalDTO.class));

        var response = mockMvc.perform(GET
                .content(UUID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String actualResponseBody = response.getContentAsString();
        assertEquals(expectedResponseBody, actualResponseBody, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    void getAnimal_notFound() throws Exception {
       when(animalService.getAnimal(UUID)).thenThrow(AnimalNotFoundException.class);
        mockMvc.perform(GET
                        .content(UUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound());
    }
}
