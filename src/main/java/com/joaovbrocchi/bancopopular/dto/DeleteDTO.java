package com.joaovbrocchi.bancopopular.dto;

import com.joaovbrocchi.bancopopular.domain.pix.TiposDeChavePix;
import com.joaovbrocchi.bancopopular.domain.pix.TiposDeConta;

import java.util.UUID;

public record DeleteDTO(
        UUID id,
        TiposDeChavePix tipoDeChave,
        String valorDaChave,
        TiposDeConta tiposDeConta
) {
}
