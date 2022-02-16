package br.ufrn.isaackennedy.authserver.service;

import br.ufrn.isaackennedy.authserver.domain.Person;
import br.ufrn.isaackennedy.authserver.repository.PersonRepository;
import br.ufrn.isaackennedy.authserver.security.AuthUser;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
    public static AuthUser authenticated() {
        try {
            return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
