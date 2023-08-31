package com.company.resume.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerUtil {
    public static void errorPage(HttpServletResponse resp,Exception ex){
        try {
            ex.printStackTrace();
            resp.sendRedirect("error?msg=" + ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
