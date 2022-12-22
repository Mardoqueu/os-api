package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.Cliente;
import com.mardoqueu.os.domain.OS;
import com.mardoqueu.os.domain.Tecnico;
import com.mardoqueu.os.domain.enums.Prioridade;
import com.mardoqueu.os.domain.enums.Status;
import com.mardoqueu.os.repositories.ClienteRepository;
import com.mardoqueu.os.repositories.OSRepository;
import com.mardoqueu.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OSRepository osRepository;


    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Mardoqueu Sousa Telvina", "439.275.610-61", "(99) 98234-5372");
        Cliente c1 = new Cliente(null, "Silvana Nair Aparício", "664.252.578-73", "(98) 98433-7020");
        OS os1 = new OS(null, Prioridade.BAIXA,  "Teste create OD", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
