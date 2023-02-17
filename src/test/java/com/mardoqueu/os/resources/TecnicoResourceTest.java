package com.mardoqueu.os.resources;

import com.mardoqueu.os.domain.Tecnico;
import com.mardoqueu.os.dtos.TecnicoDTO;
import com.mardoqueu.os.services.TecnicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class TecnicoResourceTest {

    @InjectMocks
    private TecnicoResource resource;

    @Mock
    private TecnicoService service;

    private Tecnico tecnico;

    private TecnicoDTO tecnicoDTO;


    public static final String OBJETO_NAO_ENCONTRAO = "Objeto não encontrao";

    public static final int ID          = 1;
    public static final String NOME     = "Mardoqueu";
    public static final String CPF      = "616.584.680-74";
    public static final String TELEFONE = "(86) 99125-9218";

    public static final String CPF_JA_CADASTRADO_NO_SISTEMA = "CPF já cadastrado no sistema";

    public static final int INDEX = 0;

    @BeforeEach
    void setUp() {
        openMocks(this);
        starTecnico();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void starTecnico(){
        tecnico = new Tecnico(ID, NOME, CPF, TELEFONE);
        tecnicoDTO = new TecnicoDTO(ID, NOME, CPF, TELEFONE);
    }
}