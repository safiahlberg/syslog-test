package com.wixia.syslog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This service is used to test the Syslog functionality.
 * The goal is to fail the service if logging is interrupted, or non-responsive.
 */
@RestController
@RequestMapping(value = "/logger")
public class LoggedService {
    private static final Logger SYSLOG = LoggerFactory.getLogger("SYSLOG");
    private static final Logger CONSOLELOG = LoggerFactory.getLogger(LoggedService.class);

    @GetMapping("/{message}")
    public ResponseEntity<String> ping(@PathVariable String message) {
        CONSOLELOG.info("Incoming ping message: {}", message);

        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<?> logToAllLoggers(@RequestBody String logMessage) {
        CONSOLELOG.info("Incoming log message: {}", logMessage);

        SYSLOG.info("Incoming log message: {}", logMessage);

        return ResponseEntity.ok(logMessage);
    }
}
