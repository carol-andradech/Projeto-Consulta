package br.edu.iff.projetoConsulta.security;

import br.edu.iff.projetoConsulta.model.Permissao;
import br.edu.iff.projetoConsulta.model.Psicologo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PsicologoDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Psicologo psicologo = repo.findByEmail(email);
        if (psicologo == null) {
            throw new UsernameNotFoundException("Psicologo não encontrado com esse email: " + email);
        }
        return new User(psicologo.getEmail(), psicologo.getSenha(), getAuthorities(psicologo.getPermissoes()));
    }

    private List<GrantedAuthority> getAuthorities(List<Permissao> lista) {
        List<GrantedAuthority> l = new ArrayList<>();
        for (Permissao p : lista) {
            l.add(new SimpleGrantedAuthority("ROLE_" + p.getNome()));
        }
        return l;
    }
}
