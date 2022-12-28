package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.Tecnico;
import com.mardoqueu.os.dtos.TecnicoDTO;
import com.mardoqueu.os.repositories.TecnicoRepository;
import com.mardoqueu.os.resources.exceptions.DataIntegrityViolationException;
import com.mardoqueu.os.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        if(findByCpf(objDTO) != null){
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados");
        }
        Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
        return repository.save(newObj);
    }
    private Tecnico findByCpf(TecnicoDTO objDTO) {
        Optional<Tecnico> obj = repository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        return null;
    }

    public Tecnico update(Integer id,@Valid TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);
        if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id){
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }


    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if(obj.getList().size() > 0 ){
            throw new DataIntegrityViolationException("Técnico possui Ordens de Serviço, e não pode ser deletado");
        }
        repository.deleteById(id);
    }
}
