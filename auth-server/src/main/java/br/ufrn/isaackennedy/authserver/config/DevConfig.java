package br.ufrn.isaackennedy.authserver.config;

import br.ufrn.isaackennedy.authserver.domain.Person;
import br.ufrn.isaackennedy.authserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public boolean insertData() {
        Person person = new Person("090.283.624-28", "Isaac");
        personRepository.save(person);
        return true;
    }

}
