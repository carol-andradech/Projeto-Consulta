package br.edu.iff.projetoConsulta.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paciente extends Pessoa {
    @Column(nullable = false, unique = true, length = 200)
    @NotBlank(message = "Prontuário necessário.")
    private String prontuario;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas = new ArrayList<>();


    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
