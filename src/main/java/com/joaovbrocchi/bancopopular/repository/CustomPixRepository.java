package com.joaovbrocchi.bancopopular.repository;

import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import com.joaovbrocchi.bancopopular.domain.pix.ParametrosDeConsulta;

import java.util.List;
import java.util.Optional;

public interface CustomPixRepository {
    Optional<ChavePix> findByParameterAndValue(ParametrosDeConsulta parameter, Object value);
}
