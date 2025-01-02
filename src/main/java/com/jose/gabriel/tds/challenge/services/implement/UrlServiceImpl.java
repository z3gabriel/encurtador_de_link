package com.jose.gabriel.tds.challenge.services.implement;

import com.jose.gabriel.tds.challenge.domain.UrlRequest;
import com.jose.gabriel.tds.challenge.domain.UrlResponse;
import com.jose.gabriel.tds.challenge.entities.UrlEntity;
import com.jose.gabriel.tds.challenge.exceptions.UrlNotFoundException;
import com.jose.gabriel.tds.challenge.repository.UrlRepository;
import com.jose.gabriel.tds.challenge.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public UrlResponse shortenUrl(UrlRequest data, HttpServletRequest request) {
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, data.url(), LocalDateTime.now().plusMinutes(1)));

        String redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);

        return new UrlResponse(data.url(), redirectUrl);
    }

    @Override
    public HttpHeaders redirect(String id) {
        UrlEntity url = urlRepository.findById(id)
                .orElseThrow(() -> new UrlNotFoundException(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));
        return headers;
    }
}