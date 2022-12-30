package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.OS;
import com.mardoqueu.os.repositories.OSRepository;
import com.mardoqueu.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository repository;

    public OS findById(Integer id){
        Optional<OS> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll(){
        return repository.findAll();
    }
}
