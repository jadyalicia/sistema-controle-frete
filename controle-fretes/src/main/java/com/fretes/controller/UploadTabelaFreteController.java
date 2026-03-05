package com.fretes.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fretes.service.UploadTabelaFreteService;

@RestController
@RequestMapping("/upload-tabela-frete")
@CrossOrigin("*")
public class UploadTabelaFreteController {

    private final UploadTabelaFreteService service;

    public UploadTabelaFreteController(UploadTabelaFreteService service) {
        this.service = service;
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {

        service.upload(file);

        return "Tabela importada com sucesso!";
    }
}