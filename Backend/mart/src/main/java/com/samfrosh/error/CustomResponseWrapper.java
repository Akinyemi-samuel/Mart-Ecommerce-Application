package com.samfrosh.error;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.Writer;

public class CustomResponseWrapper extends HttpServletResponseWrapper {

    public CustomResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException, IOException {
        if (sc == HttpServletResponse.SC_FORBIDDEN) {
            // Modify the response as needed
            setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            setContentType(MediaType.APPLICATION_JSON_VALUE);
            Writer writer = getWriter();
            writer.write("{\"error\": \"User not authorized\"}");
            writer.flush();
            writer.close();
        } else {
            super.sendError(sc, msg);
        }
    }
}
