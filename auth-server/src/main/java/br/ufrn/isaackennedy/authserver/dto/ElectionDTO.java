package br.ufrn.isaackennedy.authserver.dto;

import br.ufrn.isaackennedy.authserver.domain.Election;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ElectionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endDate;

    public ElectionDTO() {}

    public ElectionDTO(Election election) {
        this.id = election.getId();
        this.description = election.getDescription();
        this.startDate = election.getStartDate();
        this.endDate = election.getEndDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
