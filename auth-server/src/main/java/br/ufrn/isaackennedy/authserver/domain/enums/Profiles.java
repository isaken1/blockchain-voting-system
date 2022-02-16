package br.ufrn.isaackennedy.authserver.domain.enums;

public enum Profiles {
    VOTER(1, "ROLE_VOTER"),
    CANDIDATE(2, "ROLE_CADIDATE"),
    AUDITOR(1, "ROLE_AUDITOR");

    private int cod;
    private String descricao;

    private Profiles(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Profiles toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Profiles x : Profiles.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
