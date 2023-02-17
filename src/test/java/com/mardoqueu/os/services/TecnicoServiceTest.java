package com.mardoqueu.os.services;

import com.mardoqueu.os.domain.Cliente;
import com.mardoqueu.os.domain.Tecnico;
import com.mardoqueu.os.dtos.ClienteDTO;
import com.mardoqueu.os.dtos.TecnicoDTO;
import com.mardoqueu.os.repositories.TecnicoRepository;
import com.mardoqueu.os.resources.exceptions.DataIntegrityViolationException;
import com.mardoqueu.os.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class TecnicoServiceTest {

    public static final String OBJETO_NAO_ENCONTRAO = "Objeto não encontrao";
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

    public static final String CPF_JA_CADASTRADO_NO_SISTEMA = "CPF já cadastrado no sistema";

    public static final int INDEX = 0;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starTecnico();
    }

    @Test
    void whenFindByIdThenReturnAnTecnicoInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalTecnico);

        Tecnico response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Tecnico.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CPF, response.getCpf());
        assertEquals(TELEFONE, response.getTelefone());
    }

    @Test
   void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(any())).thenThrow(new ObjectNotFoundException(
                OBJETO_NAO_ENCONTRAO));

        try{
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRAO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfTecnicos() {
        when(repository.findAll()).thenReturn(List.of(tecnico));


        List<Tecnico> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Tecnico.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(CPF, response.get(INDEX).getCpf());
        assertEquals(TELEFONE, response.get(INDEX).getTelefone());
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(repository.save(any())).thenReturn(tecnico);

        Tecnico response = service.create(tecnicoDTO);
        assertNotNull(response);
        assertEquals(Tecnico.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CPF, response.getCpf());
        assertEquals(TELEFONE, response.getTelefone());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException(){
        when(repository.findByCpf(any())).thenReturn(optionalTecnico);

        try{
            optionalTecnico.get().setId(2);
            service.create(tecnicoDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(CPF_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        Integer id = 1;
        TecnicoDTO objDTO = new TecnicoDTO(id,"John Doe", "12345678910", "555-555-5555");
        Tecnico oldObj = new Tecnico(id, "Jane Doe", "09876543210", "555-555-5554");

        when(repository.findById(id)).thenReturn(Optional.of(oldObj));
        when(repository.save(any(Tecnico.class))).thenReturn(oldObj);

        Tecnico updatedCliente = service.update(id, objDTO);

        assertEquals(objDTO.getNome(), updatedCliente.getNome());
        assertEquals(objDTO.getCpf(), updatedCliente.getCpf());
        assertEquals(objDTO.getTelefone(), updatedCliente.getTelefone());
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