package com.martishyn.mockserver.provider;

import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class DefaultUrlProvider implements UrlProvider {

    @Override
    public URL getUrlFromFile(String fileName, Class<?> clazz) {
        return clazz.getClassLoader().getResource(fileName);
    }
}
