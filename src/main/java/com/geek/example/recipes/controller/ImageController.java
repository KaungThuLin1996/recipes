package com.geek.example.recipes.controller;

import com.geek.example.recipes.command.RecipeCommand;
import com.geek.example.recipes.service.ImageService;
import com.geek.example.recipes.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/imageFileUpload";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable("id") String id, @RequestParam("imageFile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("/recipe/{id}/recipeImage")
    public void renderImageFromDb(@PathVariable("id") String id, HttpServletResponse response) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        byte[] byteArray = new byte[recipeCommand.getImage().length];
        int i = 0;
        for (Byte wrappedByte : recipeCommand.getImage()) {
            byteArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        try {
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
