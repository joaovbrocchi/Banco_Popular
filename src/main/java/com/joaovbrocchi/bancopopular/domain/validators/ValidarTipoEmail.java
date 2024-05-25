package com.joaovbrocchi.bancopopular.domain.validators;

import com.joaovbrocchi.bancopopular.domain.exception.ChavePixException;
import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import com.joaovbrocchi.bancopopular.repository.PixRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
public class ValidarTipoEmail implements ValidarValorDaChave{
    String email;
    PixRepository pixRepository;

    public ValidarTipoEmail(String email,PixRepository pixRpository) {
        super();
        this.email = email;
        this.pixRepository = pixRpository;

    }

    @Override
    public boolean checarSeOValorJaFoiCadasrado() {
        Optional<ChavePix> chaveExistente = this.pixRepository.findByValorDaChavePix(email);
        if (chaveExistente.isPresent()) {
            throw new ChavePixException("A chave já foi cadastrada");
        }
        return true;
    }

    @Override
    public boolean validarValor() {
        this.checarSeOValorJaFoiCadasrado();
        this.validarEmail();
        return true;
    }
    private void validarEmail() {
        // Padrão regex para validar o formato do e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compila o regex em um Pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Faz a correspondência do padrão com o e-mail
        Matcher matcher = pattern.matcher(email);

        // Verifica se o e-mail corresponde ao padrão
        if (!matcher.matches()) {
            throw new ChavePixException("E-mail inválido");
        }
    }
}
