package com.martishyn.mockserver.resource;

import com.martishyn.mockserver.provider.UrlProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("v1/api/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final UrlProvider urlProvider;

    @GetMapping("/json-file")
    public ResponseEntity<?> getJsonFile() {
        var fileResource = urlProvider.getUrlFromFile("files/data.json", this.getClass());
        if (fileResource == null) {
            return ResponseEntity.notFound().build();
        }
        File file = new File(fileResource.getFile());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(file);
    }
}
