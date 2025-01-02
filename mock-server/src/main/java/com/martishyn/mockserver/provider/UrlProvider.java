package com.martishyn.mockserver.provider;

import java.net.URL;
import java.nio.file.Path;

public interface UrlProvider {

    Path getUrlFromFile(String fileName, Class<?> clazz);
}
