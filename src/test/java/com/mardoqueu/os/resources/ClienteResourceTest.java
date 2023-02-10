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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


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
    void whenFindByIdThenReturnSucess() {
        when(service.findById(anyInt())).thenReturn(cliente);

        Cliente response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Cliente.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CPF, response.getCpf());
        assertEquals(TELEFONE, response.getTelefone());
    }

    @Test
    void whenFindAllThenReturnAListofClientesDTO() {
        when(service.findAll()).thenReturn(List.of(cliente));

        ResponseEntity<List<ClienteDTO>> response = resource.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(ClienteDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(CPF, response.getBody().get(INDEX).getCpf());
        assertEquals(TELEFONE, response.getBody().get(INDEX).getTelefone());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(cliente);

        ResponseEntity<ClienteDTO> response = resource.create(clienteDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));;
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