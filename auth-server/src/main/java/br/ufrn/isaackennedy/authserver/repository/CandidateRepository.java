package br.ufrn.isaackennedy.authserver.repository;

import br.ufrn.isaackennedy.authserver.domain.Candidate;
import br.ufrn.isaackennedy.authserver.domain.Election;
import br.ufrn.isaackennedy.authserver.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Transactional(readOnly = true)
    List<Candidate> findAllByElection(Election election);
}
