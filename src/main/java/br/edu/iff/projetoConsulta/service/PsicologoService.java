package br.edu.iff.projetoConsulta.service;

import br.edu.iff.projetoConsulta.exception.NotFoundException;
import br.edu.iff.projetoConsulta.model.Assistente;
import br.edu.iff.projetoConsulta.model.Pessoa;
import br.edu.iff.projetoConsulta.model.Psicologo;
import br.edu.iff.projetoConsulta.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class PsicologoService {

    @Autowired
    private PsicologoRepository repo;

    public List<Psicologo> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }

    public List<Psicologo> findAll(){
        return repo.findAll();
    }

    public Psicologo findById(Long id){
        Optional<Psicologo> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Psicologo não encontrado. ");
        }
        return result.get();
    }

    public Psicologo save(Psicologo psi){
        verificaCpfEmailCadastrado(psi.getCpf(), psi.getEmail());

        try{
            return repo.save(psi);
        }catch (Exception ex){
            throw new RuntimeException("Falha ao salvar Psicologo. ");
        }
    }

    public void delete(Long id){
        Psicologo obj = findById(id);
        //Verifica se há consultas marcadas
        verificaExclusaoPsicologoComConsulta(obj);

        try {
            repo.delete(obj);
        } catch (Exception psi) {
            throw new RuntimeException("Falha ao excluir Psicologo. ");
        }
    }

    public Psicologo update(Psicologo psi){
        //Verifica se Psicologo já existe
        Psicologo obj = findById(psi.getId());
        //Verifica alteração da senha
        //alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try{
            psi.setCpf(obj.getCpf());
            psi.setEmail(obj.getEmail());
            psi.setSenha(obj.getSenha());
            return repo.save(psi);
        }catch (Exception ex){
            Throwable t = ex;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar Psicologo. ");
        }
    }

    private void verificaCpfEmailCadastrado(String cpf, String email){
        List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
        if(!result.isEmpty()){
            throw new RuntimeException("CPF ou Email já cadastrados.");
        }
    }

    private void verificaExclusaoPsicologoComConsulta(Psicologo psi){
        if(!psi.getConsultas().isEmpty()){
            throw new RuntimeException("Psicologo possui Consulta marcada. Não pode ser excluido.");
        }
    }

    private void alterarSenha(Assistente obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()){
            if(!senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("Senha atual está incorreta. ");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("Senhas não conferem. ");
            }
            obj.setSenha(novaSenha);
        }
    }

}
