package com.joaovbrocchi.bancopopular.domain.service;

import com.joaovbrocchi.bancopopular.domain.exception.ChavePixException;
import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import com.joaovbrocchi.bancopopular.domain.pix.TiposDeChavePix;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarTipoCPF;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarTipoCelular;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarTipoEmail;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarValorDaChave;
import com.joaovbrocchi.bancopopular.dto.InclusaoDTO;
import com.joaovbrocchi.bancopopular.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PixService {
    private PixRepository pixRepository;

    @Autowired
    public PixService(PixRepository pixRepository) {
        this.pixRepository = pixRepository;

    }


    public UUID criarChavePix(InclusaoDTO dados) {
        validarRegrasDeCadastroDaChave(dados);
        ChavePix novaChave = this.transformaDtoEmChavePix(dados);
        this.salvarNoBancoDeDados(novaChave);
        return novaChave.getChaveId();
    }

    private ChavePix transformaDtoEmChavePix(InclusaoDTO dados) {
        return ChavePix.builder()
                .tipoDeChavePix(dados.tipoChave()) // Define o tipo de chave
                .valorDaChavePix(dados.valorChave())
                .numeroDaContaQueAChavePertence(dados.numeroConta())// Define o valor da chave
                .build(); // Constrói o objeto ChavePix com os atributos configurados
    }

    private ChavePix salvarNoBancoDeDados(ChavePix novaChavePix){
       return pixRepository.save(novaChavePix);
    }


    private boolean validarRegrasDeCadastroDaChave(InclusaoDTO dados){
       this.validarValoresDasChaves(dados);
       this.checarSeAContaAindaPossuiEspacoParaMaisChaves(dados);
       this.checarSeEssaChaveEstaDisponivel(dados.valorChave());
       return true;
    };

    private boolean validarValoresDasChaves(InclusaoDTO dados){
        if(dados.tipoChave() == TiposDeChavePix.cpf){
            ValidarValorDaChave validarTipoCPF =  new ValidarTipoCPF(dados.valorChave(), pixRepository);
            return validarTipoCPF.validarValor();
        };
        if(dados.tipoChave() == TiposDeChavePix.email){
            ValidarValorDaChave validarTipoEmail =  new ValidarTipoEmail(dados.valorChave(), pixRepository);
            return validarTipoEmail.validarValor();
        };
        if(dados.tipoChave() == TiposDeChavePix.celular) {
            ValidarValorDaChave validarTipoCelular = new ValidarTipoCelular(dados.valorChave(), pixRepository);
            return validarTipoCelular.validarValor();
        }

        return  false;

    }


    private boolean checarSeAContaAindaPossuiEspacoParaMaisChaves(InclusaoDTO dados) {
        Optional<List<ChavePix>> chavesPixOptional = pixRepository.findAllByNumeroDaContaQueAChavePertence(dados.numeroConta());
        List<ChavePix> chavesPix = chavesPixOptional.orElse(Collections.emptyList());

        long numeroMaximoChaves = dados.tipoConta().equals("pj") ? 20 : 5;

        if (chavesPix.size() >= numeroMaximoChaves) {
            throw new ChavePixException("A conta já atingiu o número máximo de chaves possíveis");
        }
        return true;
    }



    private  boolean checarSeEssaChaveEstaDisponivel(String valorDaChave) {
         if(!pixRepository.findByValorDaChavePix(valorDaChave).isEmpty()){
             throw new ChavePixException("A chave não está disponivel");
         }
         return true;
    }



}
