package br.ufrn.isaackennedy.authserver.security;

import br.ufrn.isaackennedy.authserver.domain.Person;
import br.ufrn.isaackennedy.authserver.domain.enums.Profiles;
import br.ufrn.isaackennedy.authserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String cpf = authentication.getCredentials() + "";

        Person person = null;

        person = personRepository.findByCpf(cpf);

        if (person == null) {
            throw new BadCredentialsException("1000");
        }

        Set<Profiles> profiles = person.getProfiles();
        return new CPFAuthenticationToken(person.getCpf(), person.getCpf(),
                profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
