package com.pruebaBack.PruebaBackDesarrolladorJr.Controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class homeController {
    @GetMapping()
    public ResponseEntity<?> home(){
        Map<String, Object> endpoints = new HashMap<>();
        endpoints.put("GetAllProducts", "https://yeimerserranobackendjava.onrender.com/nexsys/v1/products");
        endpoints.put("GetAllProducts", "https://yeimerserranobackendjava.onrender.com/nexsys/v1/categories");
        endpoints.put("PostProduct", "https://yeimerserranobackendjava.onrender.com/nexsys/v1/products");

        Map<String, Object> response = new HashMap<>();
        response.put("Autor", "Yeimer Serrano");
        response.put("Pagina principal", "https://yeimerserranobackendjava.onrender.com");
        response.put("Endpoints", endpoints);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
