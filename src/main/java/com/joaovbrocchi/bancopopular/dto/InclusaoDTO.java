package com.joaovbrocchi.bancopopular.dto;

import com.joaovbrocchi.bancopopular.domain.pix.TiposDeChavePix;

public record InclusaoDTO(
        TiposDeChavePix tipoChave,
        String valorChave,
        String tipoConta,
        int numeroAgencia,
        String  numeroConta,
        String nomeCorrentista,
        String sobrenomeCorrentista
) {}
