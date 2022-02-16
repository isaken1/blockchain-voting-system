package br.ufrn.isaackennedy.authserver.dto;

import br.ufrn.isaackennedy.authserver.domain.Candidate;

import java.io.Serializable;

public class CandidateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private Long candidateId;

    private Long electionId;

    public CandidateDTO () {}

    public CandidateDTO(Candidate candidate) {
        this.name = candidate.getPerson().getName();
        this.candidateId = candidate.getId();
        this.electionId = candidate.getElection().getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }
}
