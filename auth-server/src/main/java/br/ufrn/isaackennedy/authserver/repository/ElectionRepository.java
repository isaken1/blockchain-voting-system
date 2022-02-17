package br.ufrn.isaackennedy.authserver.repository;

import br.ufrn.isaackennedy.authserver.domain.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    @Query(value = "SELECT e FROM Election e WHERE CURRENT_TIMESTAMP BETWEEN e.startDate AND e.endDate")
    List<Election> findAllCurrentlyActive();

}
