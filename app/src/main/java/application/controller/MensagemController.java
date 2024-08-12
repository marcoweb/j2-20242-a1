package application.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class MensagemController {
    private String[] frutas = {"Maçã", "Banana", "Morango"};

    @GetMapping("/frutas")
    public String[] list() {
        return this.frutas;
    }


    @GetMapping("/frutas/{id}")
    public String list(@PathVariable int id) {
        return this.frutas[id];
    }
}