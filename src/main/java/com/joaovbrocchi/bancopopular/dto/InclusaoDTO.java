package com.joaovbrocchi.bancopopular.dto;

public record InclusaoDTO(
        String tipoChave,
        String valorChave,
        String tipoConta,
        int numeroAgencia,
        int numeroConta,
        String nomeCorrentista,
        String sobrenomeCorrentista
) {}
