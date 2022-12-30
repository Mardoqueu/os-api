package com.mardoqueu.os.resources;

import com.mardoqueu.os.dtos.OSDTO;
import com.mardoqueu.os.services.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
