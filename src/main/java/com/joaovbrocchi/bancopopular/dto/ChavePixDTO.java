package com.joaovbrocchi.bancopopular.dto;

import com.joaovbrocchi.bancopopular.domain.pix.TiposDeChavePix;
import com.joaovbrocchi.bancopopular.domain.pix.TiposDeConta;

import java.util.UUID;

public record ChavePixDTO(
        UUID id,
        TiposDeChavePix tipoChave,
        String valorChave,
        TiposDeConta tipoConta,
        String numeroAgencia,
        String  numeroConta,
        String nomeCorrentista,
        String sobrenomeCorrentista
) {
}
