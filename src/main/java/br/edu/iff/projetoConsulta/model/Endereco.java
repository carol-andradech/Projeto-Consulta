package br.edu.iff.projetoConsulta.model;
import br.edu.iff.projetoConsulta.annotation.CEPValidation;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "Rua obrigatória.")
    @Length(max = 200, message = "Rua deve ter no máximo 200 caracteres.")
    private String rua;
    @Column()
    @Digits(integer = 4, fraction = 0, message = "Número deve ser inteiro e ter até 4 dígitos.")
    private int numero;
    @Column(length = 50, nullable = false)
    @NotBlank(message = "Bairro obrigatório.")
    @Length(max = 50, message = "Bairro deve ter no máximo 50 caracteres.")
    private String bairro;
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Cidade obrigatória.")
    @Length(max = 50, message = "Cidade deve ter no máximo 50 caracteres.")
    private String cidade;
    @Column(nullable = false, length = 9)
    @NotBlank(message = "O CEP é obrigatório.")
    @Length(min = 9, max = 9, message = "CEP deve ter exatamente 9 caracteres (Ex: 99999-999.")
    @CEPValidation(message = "CEP inválido.")
    private String cep;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        if (numero != endereco.numero) return false;
        if (!rua.equals(endereco.rua)) return false;
        if (!bairro.equals(endereco.bairro)) return false;
        if (!cidade.equals(endereco.cidade)) return false;
        return cep.equals(endereco.cep);
    }

    @Override
    public int hashCode() {
        int result = rua.hashCode();
        result = 31 * result + numero;
        result = 31 * result + bairro.hashCode();
        result = 31 * result + cidade.hashCode();
        result = 31 * result + cep.hashCode();
        return result;
    }
}