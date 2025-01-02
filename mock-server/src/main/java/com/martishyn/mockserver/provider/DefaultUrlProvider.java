package com.martishyn.mockserver.provider;

import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DefaultUrlProvider implements UrlProvider {

    @Override
    public Path getUrlFromFile(String fileName, Class<?> clazz) {
        try {
            return Paths.get(clazz.getClassLoader().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
