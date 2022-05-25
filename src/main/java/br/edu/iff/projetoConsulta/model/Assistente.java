package br.edu.iff.projetoConsulta.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import java.util.ArrayList;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Assistente extends Pessoa {
    @Column(nullable = false)
    @Length(max = 20, message = "Matricula deve ter no máximo 20 caracteres")
    @NotBlank(message = "Matrícula necessária")
    private String matricula;
    @Column(nullable = false)
    @NotBlank(message = "Senha obrigatória.")
    @Length(min = 8, message = "Senha deve ter no mímino 8 caracteres.")
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "assistente")
    private List<Consulta> consultas = new ArrayList<>();


    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }
}
