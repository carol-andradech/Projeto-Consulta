package br.edu.iff.projetoConsulta.model;
import br.edu.iff.projetoConsulta.annotation.EmailValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "Nome obrigatório.")
    @Length(max = 150, message = "Nome deve ter no máximo 150 caracteres.")
    private String nome;
    @Column(nullable = false, length = 14, unique = true, updatable = false)
    @CPF(message = "CPF inválido.")
    private String cpf;

    @Column(nullable = false, length = 100, unique = true, updatable = false)
    @NotBlank(message = "Email obrigaório.")
    @EmailValidation(message = "Email inválido.")
    private String email;

    @Embedded
    @NotNull(message = "Endereço obrigatório.")
    @Valid
    private Endereco endereco;
    @ElementCollection
    @Valid
    private List<Contato> contatos = new ArrayList();

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

        Pessoa pessoa = (Pessoa) o;

        return id.equals(pessoa.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}