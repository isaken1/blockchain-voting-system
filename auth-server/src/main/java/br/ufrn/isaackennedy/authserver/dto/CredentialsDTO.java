package br.ufrn.isaackennedy.authserver.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cpf;

    public CredentialsDTO() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
