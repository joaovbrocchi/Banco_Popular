package com.joaovbrocchi.bancopopular.domain.conta;

import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;

import java.util.List;

public abstract class Conta {
    private Number numeroConta;
    private Agencia agencia;
    private String nomeCorrentista;
    private String sobrenomeCorrentista;
    private List<ChavePix> chavesPix;

    // Construtor da classe Conta
    public Conta(Number numeroConta, Agencia agencia, String nomeCorrentista, String sobrenomeCorrentista) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.nomeCorrentista = nomeCorrentista;
        this.sobrenomeCorrentista = sobrenomeCorrentista;
    }
}
