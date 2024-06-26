package com.joaovbrocchi.bancopopular.controller;

import com.joaovbrocchi.bancopopular.domain.service.PixService;
import com.joaovbrocchi.bancopopular.dto.ChavePixDTO;
import com.joaovbrocchi.bancopopular.dto.ConsultaDTO;
import com.joaovbrocchi.bancopopular.dto.DeleteDTO;
import com.joaovbrocchi.bancopopular.dto.InclusaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/pix/chave")
public class PixController {

    private final PixService pixService;

    @Autowired
    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @Operation(summary = "Cria uma nova chave Pix")
    @PostMapping
    public ResponseEntity<UUID> rotaParaInclusaoDeChave(@RequestBody InclusaoDTO dados) {
        UUID idDaNovaChave = pixService.criarChavePix(dados);
        return new ResponseEntity<>(idDaNovaChave, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza uma chave Pix existente")
    @PutMapping
    public ResponseEntity<ChavePixDTO> rotaParaatualizarChavePix(@RequestBody ChavePixDTO dados) {
        ChavePixDTO novaChavePix = pixService.alterarChavePix(dados);
        return new ResponseEntity<>(novaChavePix, HttpStatus.OK);
    }

    @Operation(summary = "Consulta uma chave Pix")
    @GetMapping
    public ResponseEntity<ChavePixDTO> rotaParaconsultarChavePix(@RequestBody ConsultaDTO dados){
        ChavePixDTO chavePix = pixService.consultarChavePix(dados);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @Operation(summary = "Deleta uma chave Pix")
    @DeleteMapping
    public ResponseEntity<DeleteDTO>  rotaParaDeletarChavePix(@RequestBody UUID idChavePix){
        DeleteDTO chaveDeletada = pixService.deletarChavePix(idChavePix);
        return new ResponseEntity<>(chaveDeletada, HttpStatus.OK);
    }
}
