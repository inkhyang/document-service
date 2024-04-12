package com.inkhyang.documentservice;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {
    String upload(MultipartFile file);
    void delete(String file);
}
