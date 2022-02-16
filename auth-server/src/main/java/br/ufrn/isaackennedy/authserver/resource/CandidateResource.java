package br.ufrn.isaackennedy.authserver.resource;

import br.ufrn.isaackennedy.authserver.domain.Candidate;
import br.ufrn.isaackennedy.authserver.domain.Election;
import br.ufrn.isaackennedy.authserver.dto.CandidateDTO;
import br.ufrn.isaackennedy.authserver.dto.ElectionDTO;
import br.ufrn.isaackennedy.authserver.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/candidate")
public class CandidateResource {

    @Autowired
    private CandidateRepository repository;


    @GetMapping(value = "/{electionId}")
    public ResponseEntity<List<CandidateDTO>> findAllInElection(@PathVariable(value = "electionId") Long id) {
        List<Candidate> candidates = repository.findAllByElection(id);
        List<CandidateDTO> dtos = new ArrayList<>();
        for (Candidate candidate : candidates) {
            dtos.add(new CandidateDTO(candidate));
        }

        return ResponseEntity.ok(dtos);
    }


}
