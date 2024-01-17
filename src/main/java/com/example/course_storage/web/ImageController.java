package com.example.course_storage.web;

import com.example.course_storage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor

@Controller
@RequestMapping("")
public class ImageController {

    private final ImageService imageService;

    //public static String UPLOAD_DIRECTORY = System.getProperty("user.dir"); //+ "/uploads";
    public static String UPLOAD_DIRECTORY = "C:/Users/Igor/IdeaProjects/course_Storage/src/main/resources/static/images";

    @GetMapping("/image")
    public ModelAndView showImage() {
        ModelAndView modelAndView = new ModelAndView("image");
        return modelAndView;
    }


    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        //String s = imageService.uploadImage(file);
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        model.addAttribute("image", bytes);
        //model.addAttribute("result",s );
        return "image";
    }

    @GetMapping("/show/image")
    public String showImagefromDB(Model model) throws UnsupportedEncodingException {
        byte[] bytes = imageService.downloadImage("6.png");
        // byte[] bytes = imageService.downloadImage("637 АИС Эндопротезирование.pdf");
        byte[] encodeBase64 = Base64.encode(bytes);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("image", base64Encoded );
        return "imageDB";
    }
    @GetMapping("/show/pdf")
    public String showImagefromDB2(Model model) throws UnsupportedEncodingException {
        //byte[] bytes = imageService.downloadImage("6.png");
         byte[] bytes = imageService.downloadImage("637 АИС Эндопротезирование.pdf");
        //byte[] encodeBase64 = Base64.encode(bytes);
        //String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("image", bytes );
        return "imageDB";
    }
}
