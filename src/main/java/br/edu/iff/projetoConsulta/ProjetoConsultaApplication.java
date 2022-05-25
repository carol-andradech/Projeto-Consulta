package br.edu.iff.projetoConsulta;

import br.edu.iff.projetoConsulta.model.Consulta;
import br.edu.iff.projetoConsulta.model.Contato;
import br.edu.iff.projetoConsulta.model.Psicologo;
import br.edu.iff.projetoConsulta.model.Paciente;
import br.edu.iff.projetoConsulta.model.Assistente;
import br.edu.iff.projetoConsulta.model.Endereco;
import br.edu.iff.projetoConsulta.model.Pessoa;
import br.edu.iff.projetoConsulta.model.TipoAbordagemEnum;
import br.edu.iff.projetoConsulta.repository.AssistenteRepository;
import br.edu.iff.projetoConsulta.repository.ConsultaRepository;
import br.edu.iff.projetoConsulta.repository.PacienteRepository;
import br.edu.iff.projetoConsulta.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoConsultaApplication implements CommandLineRunner {

	@Autowired
	private AssistenteRepository assistenteRepo;
	@Autowired
	private PacienteRepository pacienteRepo;
	@Autowired
	private PsicologoRepository psicologoRepo;
	@Autowired
	private ConsultaRepository consultaRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoConsultaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Assistente
		Assistente a1 = new Assistente();
		a1.setNome("Jim Halpert");
		a1.setCpf("449.672.510-05");
		a1.setEmail("jimhalpert@gmail.com");
		a1.setMatricula("1234");
		a1.setSenha("12345678");

		Contato t1 = new Contato();
		t1.setTelefone("(22)99999-9999");
		Contato t2 = new Contato();
		t2.setTelefone("(22)88888-8888");

		a1.setContatos(List.of(t1,t2));

		Endereco end = new Endereco();
		end.setRua("Rua Dunder Mifflin");
		end.setNumero(15);
		end.setBairro("Scranton");
		end.setCidade("Pensilvania");
		end.setCep("28000-000");

		a1.setEndereco(end);
		assistenteRepo.save(a1);

		//Paciente
		Paciente p1 = new Paciente();
		p1.setNome("Pam Beesly");
		p1.setEmail("pambeesly@gmail.com");
		p1.setCpf("506.159.060-01");
		p1.setEndereco(end);
		p1.setContatos(List.of(t1,t2));
		p1.setProntuario("1423");

		pacienteRepo.save(p1);

		//Psicologo
		Psicologo psi1 = new Psicologo();
		psi1.setNome("Michael Scott");
		psi1.setCpf("870.418.610-90");
		psi1.setCrp("05/00001");
		psi1.setEndereco(end);
		psi1.setEmail("michaelscott@gmail.com");
		psi1.setContatos(List.of(t1,t2));
		psi1.setTipo(TipoAbordagemEnum.PSICANALISE);
		psi1.setSenha("11223344");
		psicologoRepo.save(psi1);

		//Consulta
		Consulta c1 = new Consulta();
		c1.setDataHora(LocalDateTime.parse("2022-10-10T15:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		c1.setValor(Float.parseFloat("300.00"));
		c1.setAssistente(a1);
		c1.setPaciente(p1);
		c1.setPsicologo(psi1);

		consultaRepo.save(c1);
	}

}
