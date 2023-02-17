package com.mardoqueu.os.resources;

import com.mardoqueu.os.domain.Tecnico;
import com.mardoqueu.os.dtos.ClienteDTO;
import com.mardoqueu.os.dtos.TecnicoDTO;
import com.mardoqueu.os.services.TecnicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
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
    void whenFindByIdThenReturnSucess() {
        when(service.findById(anyInt())).thenReturn(tecnico);

        Tecnico response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Tecnico.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CPF, response.getCpf());
        assertEquals(TELEFONE, response.getTelefone());
    }

    @Test
    void whenFindAllThenReturnAListofClientesDTO() {
        when(service.findAll()).thenReturn(List.of(tecnico));

        ResponseEntity<List<TecnicoDTO>> response = resource.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(TecnicoDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(CPF, response.getBody().get(INDEX).getCpf());
        assertEquals(TELEFONE, response.getBody().get(INDEX).getTelefone());

    }
    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(tecnico);

        ResponseEntity<TecnicoDTO> response = resource.create(tecnicoDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));;
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(service.update(anyInt(), any())).thenReturn(tecnico);

        ResponseEntity<TecnicoDTO> response = resource.update(ID, tecnicoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(TecnicoDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(CPF, response.getBody().getCpf());
        assertEquals(TELEFONE, response.getBody().getTelefone());
    }

    @Test
    void whenDeleteThenReturnSucess() {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<Void> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void starTecnico(){
        tecnico = new Tecnico(ID, NOME, CPF, TELEFONE);
        tecnicoDTO = new TecnicoDTO(ID, NOME, CPF, TELEFONE);
    }
}