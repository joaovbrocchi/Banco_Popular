CREATE DATABASE IF NOT EXISTS bancopopular;

use bancopopular;

CREATE TABLE IF NOT EXISTS ChavePix (
    chave_id BINARY(16) PRIMARY KEY,
    tipo_de_chave_pix VARCHAR(255) NOT NULL,
    valor_chave_pix VARCHAR(255) NOT NULL,
    numero_conta_que_pertence VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);
