package com.mardoqueu.os;


import com.mardoqueu.os.repositories.ClienteRepository;
import com.mardoqueu.os.repositories.OSRepository;
import com.mardoqueu.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OsApplication{

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OSRepository osRepository;
    public static void main(String[] args) {
        SpringApplication.run(OsApplication.class, args);
    }


}
