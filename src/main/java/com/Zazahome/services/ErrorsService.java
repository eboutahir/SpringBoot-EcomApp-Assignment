package com.ZazaHome.services;

import org.springframework.stereotype.Service;

@Service
public class ErrorsService {

    public String handleNotFoundError() {
        return "404 Not Found | Zazahome";
    }

    public String handleInternalServerError() {
        return "Something Went Wrong | Zazahome";
    }
}
