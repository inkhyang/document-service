package com.inkhyang.documentservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentController {
    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping
    public String upload(@RequestBody MultipartFile file){
        return service.upload(file);
    }

    @DeleteMapping("/{file}")
    public void delete(@RequestParam String file){
        service.delete(file);
    }
}
