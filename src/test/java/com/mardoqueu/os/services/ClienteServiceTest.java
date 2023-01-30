package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.Cliente;
import com.mardoqueu.os.dtos.ClienteDTO;
import com.mardoqueu.os.repositories.ClienteRepository;
import com.mardoqueu.os.repositories.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
class ClienteServiceTest {

    public static final int ID          = 1;
    public static final String NOME     = "Mardoqueu";
    public static final String CPF      = "616.584.680-74";
    public static final String TELEFONE = "(86) 99125-9218";
    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteRepository repository;

    @Mock
    private PessoaRepository pessoaRepository;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private Optional<Cliente> optionalCliente;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalCliente);

        Cliente response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Cliente.class, response.getClass());
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

    private void startCliente(){
        cliente = new Cliente(ID, NOME, CPF, TELEFONE);
        clienteDTO = new ClienteDTO(ID, NOME, CPF, TELEFONE);
        optionalCliente = Optional.of(new Cliente(ID, NOME, CPF, TELEFONE));
    }
}