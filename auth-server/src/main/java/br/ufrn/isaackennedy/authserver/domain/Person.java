package br.ufrn.isaackennedy.authserver.domain;

import br.ufrn.isaackennedy.authserver.domain.enums.Profiles;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Person implements Serializable {

    @Id
    @Column(nullable = false, length = 14)
    private String cpf;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    public Person() { addProfile(Profiles.VOTER); }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public Set<Profiles> getProfiles() {
        return profiles.stream().map(Profiles::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profiles perfil) {
        profiles.add(perfil.getCod());
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (getCpf() == null) {
            return other.getCpf() == null;
        } else return getCpf().equals(other.getCpf());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCpf() == null) ? 0 : getCpf().hashCode());
        return result;
    }
}
