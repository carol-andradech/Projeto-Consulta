package br.edu.iff.projetoConsulta.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Psicologo extends Pessoa {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "CRP necessário.")
    @Length(min = 7)
    private String crp;
    @Column(nullable = false)
    @NotBlank(message = "Senha obrigatória.")
    @Length(min = 8, message = "Senha deve ter no mímino 8 caracteres.")
    private String senha;
    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de abordagem obrigatório.")
    private TipoAbordagemEnum tipo;

    @JsonIgnore
    @OneToMany(mappedBy = "psicologo")
    private List<Consulta> consultas = new ArrayList<>();

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoAbordagemEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoAbordagemEnum tipo) {
        this.tipo = tipo;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}