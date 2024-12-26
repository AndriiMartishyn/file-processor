package com.martishyn.mockserver.resource;

import com.martishyn.mockserver.provider.UrlProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResourceController.class)
public class ResourceControllerTest {

    private static final String REQUEST_URL = "/v1/api/resource/json-file";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlProvider urlProvider;

    @MockitoBean
    private URL url;

    @DisplayName("testing-non-existing-file")
    @Test
    public void shouldReturnNotFoundResponseWhenNoFileExists() throws Exception {
        when(urlProvider.getUrlFromFile(anyString(), any())).thenReturn(null);

        mockMvc.perform(get(REQUEST_URL)).andExpect(status().isNotFound());
    }

    @DisplayName("testing-existing-file")
    @Test
    public void shouldReturnOkResponseWhenFileExists() throws Exception {
        when(urlProvider.getUrlFromFile(anyString(), any())).thenReturn(url);
        when(url.getFile()).thenReturn("/files/data.json");

        mockMvc.perform(get(REQUEST_URL)).andExpect(status().is2xxSuccessful())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().exists("Content-Disposition"))
                .andExpect(header().stringValues("Content-Disposition", "attachment; filename=data.json"))
                .andDo(print());
    }
}
