package com.joaovbrocchi.bancopopular.domain.validators;

import com.joaovbrocchi.bancopopular.domain.exception.ChavePixException;
import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import com.joaovbrocchi.bancopopular.repository.PixRepository;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarTipoCPF implements ValidarValorDaChave {
    private String cpf;
    private final PixRepository pixRepository;

    public ValidarTipoCPF(String cpf, PixRepository pixRepository) {
        this.cpf = cpf;
        this.pixRepository = pixRepository;
    }

    @Override
    public boolean validarValor() {
        checarSeOValorJaFoiCadasrado();
        validarNumeroDoCpf();
        return true;
    }
    @Override
    public boolean checarSeOValorJaFoiCadasrado() {
        Optional<ChavePix> chaveExistente = this.pixRepository.findByValorDaChavePix(cpf);
        if (chaveExistente.isPresent()) {
            throw new ChavePixException("A chave já foi cadastrada");
        }
        return true;
    }
    private boolean validarNumeroDoCpf() {
        // Padrao regex para validar CPF: XXX.XXX.XXX-XX ou apenas números (11 dígitos)
        String cpfRegex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

        // Compila o regex em um Pattern
        Pattern pattern = Pattern.compile(cpfRegex);

        // Faz a correspondência do padrão com o CPF
        Matcher matcher = pattern.matcher(cpf);

        // Verifica se o CPF corresponde ao padrão
        if (!matcher.matches()) {
            throw new ChavePixException("CPF inválido");
        }

        // Se passou pela validação, retorna true
        return true;
    }

}
