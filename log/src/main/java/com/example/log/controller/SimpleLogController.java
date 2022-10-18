package com.example.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SimpleLogController {

    @GetMapping("/log")
    public ResponseEntity<Void> log() {
        log.info("hello log!");

        log.trace("Trace");
        log.debug("Debug");
        log.info("Info");
        log.warn("Warn");
        log.error("Error");
        return ResponseEntity.ok().build();
    }
}
