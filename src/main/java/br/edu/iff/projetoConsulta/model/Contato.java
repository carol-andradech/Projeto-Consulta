package br.edu.iff.projetoConsulta.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(length = 14, nullable = false)
    @NotBlank(message = "Telefone obrigaório.")
    @Length(min = 13, max = 14, message = "Telefone deve ter 13 ou 14 caracteres (Ex: (99)9999-9999 ou (99)99999-9999).")
    private String telefone;
    @Column(nullable = false, length = 100, unique = true, updatable = false)
    @NotBlank(message = "Email obrigaório.")
    //@EmailValidation(message = "Email inválido.")
    private String email;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contato contato = (Contato) o;

        if (!telefone.equals(contato.telefone)) return false;
        return email.equals(contato.email);
    }

    @Override
    public int hashCode() {
        int result = telefone.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
