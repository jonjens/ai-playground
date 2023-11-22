package com.jens.aiplayground.controller;

import com.jens.aiplayground.service.EnvService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class OcrController {

        @Autowired
        EnvService envService;

        @PostMapping("/image")
        public ResponseEntity<String> extractTextFromImage(@RequestParam("file") MultipartFile file) {
                if (file.isEmpty()) {
                        return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
                }

                try {
                        String uploadPath = "C:\\Users\\jonas\\uploadpath\\";
                        File convFile = new File(uploadPath + file.getOriginalFilename());
                        file.transferTo(convFile);

                        ITesseract tesseract = new Tesseract();
                        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Set path to Tesseract data folder (where language data files are present)

                        String text = tesseract.doOCR(convFile);
                        convFile.delete(); // Delete temporary file

                        return new ResponseEntity<>(text, HttpStatus.OK);
                } catch (IOException e) {
                        e.printStackTrace();
                        return new ResponseEntity<>("Error occurred during image processing", HttpStatus.INTERNAL_SERVER_ERROR);
                } catch (TesseractException e) {
                        throw new RuntimeException(e);
                }
        }

        @GetMapping("/test")
        public String test(){
                return envService.getTest();
        }





}
