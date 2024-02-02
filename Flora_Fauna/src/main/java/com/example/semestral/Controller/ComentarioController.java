package com.example.semestral.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.semestral.Model.GetPost;
import com.example.semestral.Services.PostFloraFauna;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @GetMapping("/animales")
    public List<GetPost> obtenerComentariosPorAnimal() {
        return new PostFloraFauna().obtenerComentariosPorAnimal();
    }

    @GetMapping("/plantas")
    public List<GetPost> obtenerComentariosPorPlanta() {
        return new PostFloraFauna().obtenerComentariosPorPlanta();
    }
}
