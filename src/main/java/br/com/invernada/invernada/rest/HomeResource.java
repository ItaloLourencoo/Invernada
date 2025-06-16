package br.com.invernada.invernada.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HomeResource {

    @GetMapping("/")
    public String index() {
        return "\"Hello World!\"";
    }

}
