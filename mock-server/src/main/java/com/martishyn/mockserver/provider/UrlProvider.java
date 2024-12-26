package com.martishyn.mockserver.provider;

import java.net.URL;

public interface UrlProvider {

    URL getUrlFromFile(String fileName, Class<?> clazz);
}
