package br.ufrn.isaackennedy.authserver.config;

import br.ufrn.isaackennedy.authserver.domain.Candidate;
import br.ufrn.isaackennedy.authserver.domain.Election;
import br.ufrn.isaackennedy.authserver.domain.Person;
import br.ufrn.isaackennedy.authserver.repository.CandidateRepository;
import br.ufrn.isaackennedy.authserver.repository.ElectionRepository;
import br.ufrn.isaackennedy.authserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Bean
    public boolean insertData() {
        Person person = new Person("090.283.624-28", "Isaac");
        person = personRepository.save(person);

        Person person2 = new Person("123.456.789-00", "Candidato 1");
        person2 = personRepository.save(person2);

        Person person3 = new Person("123.456.789-01", "Candidato 2");
        person3 = personRepository.save(person3);

        Person person4 = new Person("123.456.789-02", "Candidato 3");
        person4 = personRepository.save(person4);

        Person person5 = new Person("123.456.789-03", "Candidato 4");
        person5 = personRepository.save(person5);

        Person person6 = new Person("123.456.789-04", "Candidato 5");
        person6 = personRepository.save(person6);

        Election election = new Election(LocalDateTime.now(), LocalDateTime.now().plusDays(2L),
                "Eleições presidenciais.");

        Election election2 = new Election(LocalDateTime.now(), LocalDateTime.now().plusDays(2L),
                "Eleições para governadores.");

        Election election3 = new Election(LocalDateTime.now(), LocalDateTime.now().plusDays(2L),
                "Eleições para prefeito");

        election = electionRepository.save(election);
        election2 = electionRepository.save(election2);
        election3 = electionRepository.save(election3);

        Candidate candidate = new Candidate(person, election);
        Candidate candidate2 = new Candidate(person2, election);
        Candidate candidate3 = new Candidate(person3, election2);
        Candidate candidate4 = new Candidate(person4, election2);
        Candidate candidate5 = new Candidate(person5, election3);
        Candidate candidate6 = new Candidate(person6, election3);

        candidateRepository.save(candidate);
        candidateRepository.save(candidate2);
        candidateRepository.save(candidate3);
        candidateRepository.save(candidate4);
        candidateRepository.save(candidate5);
        candidateRepository.save(candidate6);

        return true;
    }

}
