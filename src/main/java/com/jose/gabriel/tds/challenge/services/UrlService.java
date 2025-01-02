package com.jose.gabriel.tds.challenge.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import com.jose.gabriel.tds.challenge.domain.UrlResponse;
import com.jose.gabriel.tds.challenge.domain.UrlRequest;

public interface UrlService {

    UrlResponse shortenUrl(UrlRequest data, HttpServletRequest request);

    HttpHeaders redirect(String id);
}