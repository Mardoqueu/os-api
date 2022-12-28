package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.Cliente;
import com.mardoqueu.os.dtos.ClienteDTO;
import com.mardoqueu.os.repositories.ClienteRepository;
import com.mardoqueu.os.repositories.PessoaRepository;
import com.mardoqueu.os.repositories.TecnicoRepository;
import com.mardoqueu.os.resources.exceptions.DataIntegrityViolationException;
import com.mardoqueu.os.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: "+ Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        if(findByCpf(objDTO) != null){
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados");
        }
        Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
        return repository.save(newObj);
    }
    private Cliente findByCpf(ClienteDTO objDTO) {
        Optional<Cliente> obj = repository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        return null;
    }

    public Cliente update(Integer id,@Valid ClienteDTO objDTO) {
        Cliente oldObj = findById(id);
        if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id){
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);

        if(obj.getList().size() > 0 ){
            throw new DataIntegrityViolationException("Pessoa possui Ordens de Serviço, e não pode ser deletado");
        }
        repository.deleteById(id);
    }
}
