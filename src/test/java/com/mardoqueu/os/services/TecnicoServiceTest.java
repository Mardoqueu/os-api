package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.Cliente;
import com.mardoqueu.os.domain.Tecnico;
import com.mardoqueu.os.dtos.ClienteDTO;
import com.mardoqueu.os.dtos.TecnicoDTO;
import com.mardoqueu.os.repositories.TecnicoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
class TecnicoServiceTest {

    @InjectMocks
    private TecnicoService service;

    @Mock
    private TecnicoRepository repository;

    private Tecnico tecnico;

    private TecnicoDTO tecnicoDTO;

    private Optional<Tecnico> optionalTecnico;

    public static final int ID          = 1;
    public static final String NOME     = "Mardoqueu";
    public static final String CPF      = "616.584.680-74";
    public static final String TELEFONE = "(86) 99125-9218";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starTecnico();
    }

    @Test
    void whenFindByIdThenReturnAnTecnicoInstance() {
        Mockito.when(repository.findById(anyInt())).thenReturn(optionalTecnico);

        Tecnico response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Tecnico.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CPF, response.getCpf());
        assertEquals(TELEFONE, response.getTelefone());
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
        optionalTecnico = Optional.of(new Tecnico(ID, NOME, CPF, TELEFONE));

    }
}