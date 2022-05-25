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


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contato contato = (Contato) o;

        return telefone.equals(contato.telefone);
    }

    @Override
    public int hashCode() {
        return telefone.hashCode();
    }
}
