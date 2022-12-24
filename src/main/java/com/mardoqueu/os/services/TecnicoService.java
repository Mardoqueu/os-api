package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.Tecnico;
import com.mardoqueu.os.repositories.TecnicoRepository;
import com.mardoqueu.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: "+ Tecnico.class.getName()));
    }
}
