package com.geek.example.recipes.controller;

import com.geek.example.recipes.command.RecipeCommand;
import com.geek.example.recipes.service.ImageService;
import com.geek.example.recipes.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testShowUploadForm() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void testHandleImagePost() throws Exception {
        MockMultipartFile file = new MockMultipartFile("imageFile", "testing.txt",
                "text/plain", "Spring".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(file))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/recipe/1/show"));
        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    void testRenderImageFromDb() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;
        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }

        recipeCommand.setImage(bytesBoxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeImage"))
                                                        .andExpect(status().is2xxSuccessful())
                                                        .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, responseBytes.length);
    }
}