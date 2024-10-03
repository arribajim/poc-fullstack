package com.example.fileprocessing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v2")
public class FileWritingController {

    @GetMapping("/files")
    public ResponseEntity<List<String>> getMethodName(@RequestParam String param) {

        final Optional<List<String>> filesMock = Optional.ofNullable(new ArrayList<String>(Arrays.asList("Geeks",
                          "for",
                          "Geeks")));
        return filesMock.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
}
