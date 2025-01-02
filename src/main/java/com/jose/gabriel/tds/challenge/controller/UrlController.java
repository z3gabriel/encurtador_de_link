package com.jose.gabriel.tds.challenge.controller;

import com.jose.gabriel.tds.challenge.domain.UrlRequest;
import com.jose.gabriel.tds.challenge.domain.UrlResponse;
import com.jose.gabriel.tds.challenge.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest data, HttpServletRequest request){
        return ResponseEntity.ok(urlService.shortenUrl(data,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        HttpHeaders headers = urlService.redirect(id);
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}