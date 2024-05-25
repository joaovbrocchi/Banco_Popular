package com.joaovbrocchi.bancopopular.domain.conta;

public class ContaPJ extends Conta{
    protected static final int LIMITE_CHAVE_PIX= 20;

    public ContaPJ(Number numeroConta, Agencia agencia, Double limiteChavePix, String nomeCorrentista, String sobrenomeCorrentista) {
        super(numeroConta, agencia, limiteChavePix, nomeCorrentista, sobrenomeCorrentista);
    }
}
