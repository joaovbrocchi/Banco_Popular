package com.joaovbrocchi.bancopopular.domain.validators;

import com.joaovbrocchi.bancopopular.domain.exception.ChavePixException;
import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import com.joaovbrocchi.bancopopular.repository.PixRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ValidarTipoCelular implements ValidarValorDaChave {
    private String numeroDeCelular;
    private PixRepository pixRepository;

    static final  String[] DDDs_BRASIL = {
            "11", "12", "13", "14", "15", "16", "17", "18", "19", // São Paulo e interior
            "21", "22", "24", // Rio de Janeiro
            "27", "28", // Espírito Santo
            "31", "32", "33", "34", "35", "37", "38", // Minas Gerais
            "41", "42", "43", "44", "45", "46", // Paraná
            "47", "48", "49", // Santa Catarina
            "51", "53", "54", "55", "56", "57", "58", "59", // Rio Grande do Sul
            "61", // Distrito Federal e Goiás
            "62", "64", // Goiás
            "63", // Tocantins
            "65", "66", // Mato Grosso
            "67", // Mato Grosso do Sul
            "68", // Acre
            "69", // Rondônia
            "71", "73", "74", "75", "77", // Bahia
            "79", // Sergipe
            "81", "82", "83", "84", "85", "86", "87", "88", "89", // Pernambuco
            "91", "93", "94", // Pará
            "95", // Roraima
            "96", // Amapá
            "97", "98", "99" // Amazonas
    };

    public ValidarTipoCelular(String numeroDoCelular, PixRepository pixRepository) {
        super();
        this.numeroDeCelular = numeroDoCelular;
        this.pixRepository = pixRepository;

    }


    @Override
    public boolean validarValor() {
        this.checarSeOValorJaFoiCadasrado();
        this.validarCodigoDoBrasil();
        this.validarOddd();
        this.checarSeONumeroTemDoisDigitos();
        return true;

    }
    @Override
    public boolean checarSeOValorJaFoiCadasrado() {
        Optional<ChavePix> chaveExistente = this.pixRepository.findByValorDaChavePix(this.numeroDeCelular);

        if (chaveExistente.isPresent()) {
            throw new ChavePixException("A chave já foi cadastrada");
        }

        return true;
    }


    private boolean validarCodigoDoBrasil() {
        if (this.numeroDeCelular.length() < 2) {
            throw new ChavePixException("Inclua um código de área válido");
        }
        if (!this.numeroDeCelular.startsWith("+")) {
            this.numeroDeCelular = "+" + this.numeroDeCelular;
        }
        // Verificar se os dois primeiros dígitos são "55"
        String doisPrimeirosDigitos = this.numeroDeCelular.substring(1, 3);
        if (!doisPrimeirosDigitos.equals("55")) {
            throw new ChavePixException("O código de área deve ser '55'");
        }
        return true;

    }

    private boolean validarOddd(){
        if (this.numeroDeCelular.length() < 3) {
            throw new ChavePixException("Inclua um código DDD válido");
        }
        checarSeONumeroDoDddEValido();
        return true;
    }


    private boolean checarSeONumeroDoDddEValido() {
        // Extrai o DDD da posição correta na string do número de celular
        String ddd = this.numeroDeCelular.substring(0, 2);

        // Converte o array DDDs_BRASIL para uma lista
        List<String> dddsBrasilList = Arrays.asList(DDDs_BRASIL);

        // Verifica se o DDD está presente na lista
        if (!dddsBrasilList.contains(ddd)) {
            throw new ChavePixException("Coloque um DDD válido");
        }

        return true;
    }

    private void checarSeONumeroTemDoisDigitos(){
        if(this.numeroDeCelular.length() > 14) throw  new ChavePixException("Digite Um numero Válido");
    };


}
