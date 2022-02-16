package br.ufrn.isaackennedy.authserver.resource;

import br.ufrn.isaackennedy.authserver.domain.Election;
import br.ufrn.isaackennedy.authserver.dto.ElectionDTO;
import br.ufrn.isaackennedy.authserver.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/election")
public class ElectionResource {

    @Autowired
    private ElectionRepository repository;

    @GetMapping()
    public ResponseEntity<List<ElectionDTO>> findAllActive() {
        List<Election> elections = repository.findAllCurrentlyActive();
        List<ElectionDTO> dtos = new ArrayList<>();
        for (Election election : elections) {
            dtos.add(new ElectionDTO(election));
        }

        return ResponseEntity.ok(dtos);
    }

}
