package com.joaovbrocchi.bancopopular.controller;

import com.joaovbrocchi.bancopopular.dto.InclusaoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pix")
public class PixController {

    @PostMapping("/inclusao")
    public void rotaParaInclusaoDeChave(@RequestBody InclusaoDTO dados) {
        // Lógica para lidar com a inclusão da chave PIX
    }
}
