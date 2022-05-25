package br.edu.iff.projetoConsulta.security;

import br.edu.iff.projetoConsulta.model.Assistente;
import br.edu.iff.projetoConsulta.repository.AssistenteRepository;
import br.edu.iff.projetoConsulta.model.Permissao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AssistenteDetailsService implements UserDetailsService {
    @Autowired
    private AssistenteRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Assistente assistente = repo.findByCpfOrEmail(email);
        if (assistente == null) {
            throw new UsernameNotFoundException("Assistente não encontrado com esse email: " + email);
        }
        return new User(assistente.getEmail(), assistente.getSenha(), getAuthorities(assistente.getPermissoes()));
    }

    private List<GrantedAuthority> getAuthorities(List<Permissao> lista) {
        List<GrantedAuthority> l = new ArrayList<>();
        for (Permissao p : lista) {
            l.add(new SimpleGrantedAuthority("ROLE_" + p.getNome()));
        }
        return l;
    }
}
