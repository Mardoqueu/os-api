package com.mardoqueu.os.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "tecnico")
    private List<OS> list = new ArrayList<>();
    public Tecnico() {
        super();
    }

    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<OS> getList() {
        return list;
    }

    public void setList(List<OS> list) {
        this.list = list;
    }
}
