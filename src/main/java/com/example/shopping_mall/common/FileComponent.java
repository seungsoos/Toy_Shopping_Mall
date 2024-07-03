package com.example.shopping_mall.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class FileComponent {

    public String upload(MultipartFile multipartFile) {
        String name = multipartFile.getName();

        log.info("file upload = {}", name);
        return name;
    }
}
