package com.joaovbrocchi.bancopopular.domain.service;

import com.joaovbrocchi.bancopopular.domain.exception.ChavePixException;
import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import com.joaovbrocchi.bancopopular.domain.pix.ParametrosDeConsulta;
import com.joaovbrocchi.bancopopular.domain.pix.TiposDeChavePix;
import com.joaovbrocchi.bancopopular.domain.pix.TiposDeConta;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarTipoCPF;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarTipoCelular;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarTipoEmail;
import com.joaovbrocchi.bancopopular.domain.validators.ValidarValorDaChave;
import com.joaovbrocchi.bancopopular.dto.ChavePixDTO;
import com.joaovbrocchi.bancopopular.dto.ConsultaDTO;
import com.joaovbrocchi.bancopopular.dto.DeleteDTO;
import com.joaovbrocchi.bancopopular.dto.InclusaoDTO;
import com.joaovbrocchi.bancopopular.repository.PixRepository;
import jakarta.transaction.Transactional;
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
    public ChavePixDTO consultarChavePix(ConsultaDTO dados) {
        Optional<ChavePix> chavePixOptional = pixRepository.findByParameterAndValue(dados.parametro(), dados.valor());
        if (chavePixOptional.isPresent()) {
            ChavePix chavePix = chavePixOptional.get();
            return transformaChavePixEmDto(chavePix);
        } else {
            throw new ChavePixException("Chave Pix não encontrada");
        }
    }

    public DeleteDTO deletarChavePix(UUID idChavePix) {
        ChavePixDTO chavePix = this.consultarChavePix(new ConsultaDTO(ParametrosDeConsulta.id,idChavePix) );
        this.pixRepository.deleteById(idChavePix);
        return  new DeleteDTO(
                chavePix.id(),
                chavePix.tipoChave(),
                chavePix.valorChave(),
                chavePix.tipoConta()
        );
    }
    public UUID criarChavePix(InclusaoDTO dados) {
        ChavePix novaChave = this.transformaDtoEmChavePix(dados);
        validarRegrasDeCadastroDaChave(novaChave);
        this.salvarNoBancoDeDados(novaChave);
        return novaChave.getChaveId();
    }

    @Transactional
    public ChavePixDTO alterarChavePix(ChavePixDTO dados) {
        ChavePix chavePix = ChavePix.builder()
                .tipoDeChavePix(dados.tipoChave()) // Define o tipo de chave
                .valorDaChavePix(dados.valorChave())
                .numeroDaContaQueAChavePertence(dados.numeroConta())
                .numeroDaAgencia(dados.numeroAgencia())
                .primeiroNomeCorrentista(dados.nomeCorrentista())
                .sobrenomeCorrentista(dados.sobrenomeCorrentista())// Define o valor da chave
                .build(); // Constrói o objeto ChavePix com os atributos configurados
        validarRegrasDeAlteracaoDaChave(chavePix);
        ChavePix chavePixAtualizada = pixRepository.save(chavePix);
        return transformaChavePixEmDto(chavePixAtualizada);
    }

    private boolean validarRegrasDeAlteracaoDaChave(ChavePix dados){
        this.validarValoresDasChave(dados);
        this.checarSeEssaChaveEstaDisponivel(dados.getValorDaChavePix());
        return true;
    }

    private ChavePixDTO transformaChavePixEmDto(ChavePix chavePix) {
        return new ChavePixDTO(
                chavePix.getChaveId(),
                chavePix.getTipoDeChavePix(), // Tipo de chave
                chavePix.getValorDaChavePix(),
                chavePix.getTipoDeConta(),// Valor da chave
                chavePix.getNumeroDaContaQueAChavePertence(), // Número da conta
                chavePix.getNumeroDaAgencia(), // Número da agência
                chavePix.getPrimeiroNomeCorrentista(), // Primeiro nome do correntista
                chavePix.getSobrenomeCorrentista() // Sobrenome do correntista
        );
    }





    private ChavePix transformaDtoEmChavePix(InclusaoDTO dados) {
        return ChavePix.builder()
                .tipoDeChavePix(dados.tipoChave()) // Define o tipo de chave
                .valorDaChavePix(dados.valorChave())
                .numeroDaContaQueAChavePertence(dados.numeroConta())
                .numeroDaAgencia(dados.numeroAgencia())
                .primeiroNomeCorrentista(dados.nomeCorrentista())
                .sobrenomeCorrentista(dados.sobrenomeCorrentista())// Define o valor da chave
                .build(); // Constrói o objeto ChavePix com os atributos configurados
    }

    private ChavePix salvarNoBancoDeDados(ChavePix novaChavePix){
       return pixRepository.save(novaChavePix);
    }



    private boolean validarRegrasDeCadastroDaChave(ChavePix dados){
       this.validarValoresDasChave(dados);
       this.checarSeAContaAindaPossuiEspacoParaMaisChaves(dados);
       this.checarSeEssaChaveEstaDisponivel(dados.getValorDaChavePix());
       return true;
    };

    private boolean validarValoresDasChave(ChavePix dados){
        if(dados.getTipoDeChavePix() == TiposDeChavePix.cpf){
            ValidarValorDaChave validarTipoCPF =  new ValidarTipoCPF(dados.getValorDaChavePix(), pixRepository);
            return validarTipoCPF.validarValor();
        };
        if(dados.getTipoDeChavePix() == TiposDeChavePix.email){
            ValidarValorDaChave validarTipoEmail =  new ValidarTipoEmail(dados.getValorDaChavePix(), pixRepository);
            return validarTipoEmail.validarValor();
        };
        if(dados.getTipoDeChavePix() == TiposDeChavePix.celular) {
            ValidarValorDaChave validarTipoCelular = new ValidarTipoCelular(dados.getValorDaChavePix(), pixRepository);
            return validarTipoCelular.validarValor();
        }

        return  false;

    }


    private boolean checarSeAContaAindaPossuiEspacoParaMaisChaves(ChavePix dados) {
        Optional<List<ChavePix>> chavesPixOptional = pixRepository.findAllByNumeroDaContaQueAChavePertence(dados.getNumeroDaContaQueAChavePertence());
        List<ChavePix> chavesPix = chavesPixOptional.orElse(Collections.emptyList());

        long numeroMaximoChaves = (dados.getTipoDeConta() == TiposDeConta.pj) ? 20 : 5;

        if (chavesPix.size() >= numeroMaximoChaves) {
            throw new ChavePixException("A conta já atingiu o número máximo de chaves possíveis");
        }
        return true;
    }



    private  boolean checarSeEssaChaveEstaDisponivel(String valorDaChave) {
         if(!pixRepository.findByValorDaChavePix(valorDaChave).isEmpty()){
             throw new ChavePixException("Essa chave já esta sendo usada");
         }
         return true;
    }



}
