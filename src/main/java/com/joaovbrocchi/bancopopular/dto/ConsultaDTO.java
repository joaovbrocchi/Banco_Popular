package com.joaovbrocchi.bancopopular.dto;

import com.joaovbrocchi.bancopopular.domain.pix.ParametrosDeConsulta;

public record ConsultaDTO (
        ParametrosDeConsulta parametro,
        Object valor
) {
}
