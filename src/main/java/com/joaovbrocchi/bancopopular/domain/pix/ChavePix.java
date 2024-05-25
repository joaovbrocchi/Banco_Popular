package com.joaovbrocchi.bancopopular.domain.pix;

import com.joaovbrocchi.bancopopular.domain.conta.Conta;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
public class ChavePix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID chaveId;

    private TiposDeChavePix tipoDeChavePix;

    private String valorDaChavePix;

    @ManyToOne
    private Conta contaQueAChavePertence;

}
