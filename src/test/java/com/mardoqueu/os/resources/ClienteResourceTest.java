package com.mardoqueu.os.resources;

import com.mardoqueu.os.domain.Cliente;
import com.mardoqueu.os.dtos.ClienteDTO;
import com.mardoqueu.os.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class ClienteResourceTest {

    public static final int ID          = 1;
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado! Id: " + ID + ", Tipo: " + Cliente.class.getName();
    public static final String NOME     = "Mardoqueu";
    public static final String CPF      = "616.584.680-74";
    public static final String TELEFONE = "(86) 99125-9218";

    public static final int INDEX = 0;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private Optional<Cliente> optionalCliente;

    @InjectMocks
    private ClienteResource resource;

    @Mock
    private ClienteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
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

    private void startCliente(){
        cliente = new Cliente(ID, NOME, CPF, TELEFONE);
        clienteDTO = new ClienteDTO(ID, NOME, CPF, TELEFONE);
        optionalCliente = Optional.of(new Cliente(ID, NOME, CPF, TELEFONE));
    }
}