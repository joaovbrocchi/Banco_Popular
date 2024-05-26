package com.joaovbrocchi.bancopopular.domain.pix;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime; // Importe a classe LocalDateTime
import java.util.UUID;

@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ChavePix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID chaveId;

    @Enumerated(EnumType.STRING)
    private TiposDeChavePix tipoDeChavePix;

    @Column(name = "valor-chave-pix")
    private String valorDaChavePix;

    @Column(name = "tipo-conta")
    private TiposDeConta tipoDeConta;

    @Column(name= "numero-agencia")
    private String numeroDaAgencia;

    @Column(name = "numero-conta-que-pertence")
    private String  numeroDaContaQueAChavePertence;

    @Column(name = "nome-correntista")
    private String primeiroNomeCorrentista;

    @Column(name = "sobrenome-corentista")
    private String sobrenomeCorrentista;

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Adicione o campo createdAt

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
