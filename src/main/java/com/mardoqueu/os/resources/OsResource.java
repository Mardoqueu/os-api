package com.mardoqueu.os.resources;

import com.mardoqueu.os.domain.OS;
import com.mardoqueu.os.dtos.OSDTO;
import com.mardoqueu.os.services.OsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")

@RestController
@RequestMapping(value = "/os")
public class OsResource {

    @Autowired
    private OsService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
        OSDTO obj = new OSDTO((service.findById(id)));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll(){
        List<OS> list = service.findAll();
        List<OSDTO> listDTO = list.stream().map(obj -> new OSDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO objDTO){
        objDTO = new OSDTO(service.create(objDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO objDTO){
        objDTO = new OSDTO(service.update(objDTO));
        return ResponseEntity.ok().body(objDTO);
    }
}
