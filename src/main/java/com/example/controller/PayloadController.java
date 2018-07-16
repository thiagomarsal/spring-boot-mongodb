package com.example.controller;

import com.example.domain.Payload;
import com.example.repository.PayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payload")
public class PayloadController {

    @Autowired
    private PayloadRepository payloadRepository;

    @RequestMapping("/save")
    public ResponseEntity<Payload> savePayload(@RequestBody Payload payload) {
        payloadRepository.save(payload);
        return ResponseEntity.ok(payload);
    }
}
