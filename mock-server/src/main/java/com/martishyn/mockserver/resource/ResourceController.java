package com.martishyn.mockserver.resource;

import com.martishyn.mockserver.provider.UrlProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("v1/api/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final UrlProvider urlProvider;

    @GetMapping("/json-file")
    public ResponseEntity<StreamingResponseBody> getJsonFile() throws IOException {
        var filePath = urlProvider.getUrlFromFile("files/dataset.json", this.getClass());
        if (filePath == null) {
            return ResponseEntity.noContent().build();
        }
        StreamingResponseBody jsonContent = out -> Files.copy(filePath, out);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonContent);
    }
}
