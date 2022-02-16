package br.ufrn.isaackennedy.authserver.service;

import br.ufrn.isaackennedy.authserver.domain.Person;
import br.ufrn.isaackennedy.authserver.repository.PersonRepository;
import br.ufrn.isaackennedy.authserver.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository repo;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Person person = repo.findByCpf(cpf);
        if (person == null) {
            throw new UsernameNotFoundException(cpf);
        }

        return new AuthUser(person.getCpf(), person.getProfiles());
    }
}
