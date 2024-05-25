package com.joaovbrocchi.bancopopular.domain.service;

import com.joaovbrocchi.bancopopular.dto.InclusaoDTO;
import com.joaovbrocchi.bancopopular.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PixService {
    private PixRepository pixRepository;

    @Autowired
    public PixService(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }
    public InclusaoDTO criarChavePix(InclusaoDTO dados){
            this.validarRegrasDeCadastroDaChave();
            return null;
    }

    public void SalvarNoBancoDeDados(){}



    public boolean  checarSeAContaAindaPossuiEspacoParaMaisChaves(){
        return false;
    };

    public boolean checarSeEssaChaveNaoEstaDisponivel(){
        return  false;
    };

    public boolean validarRegrasDeCadastroDaChave(){
        return false;
    };

    public void vincularChavePix(){};

}
