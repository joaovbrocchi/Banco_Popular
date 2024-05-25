package com.joaovbrocchi.bancopopular.domain.conta;

public class ContaPF extends Conta {

    protected static final int LIMITE_CHAVE_PIX = 5;

    public ContaPF(Number numeroConta, Agencia agencia, String nomeCorrentista, String sobrenomeCorrentista) {
        super(numeroConta, agencia, nomeCorrentista, sobrenomeCorrentista);

    }
}
