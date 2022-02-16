package br.ufrn.isaackennedy.authserver.repository;

import br.ufrn.isaackennedy.authserver.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    @Transactional(readOnly = true)
    Person findByCpf(String cpf);
}
