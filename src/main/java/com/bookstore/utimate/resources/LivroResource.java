package com.bookstore.utimate.resources;

import com.bookstore.utimate.DTO.LivroDTO;
import com.bookstore.utimate.Service.LivroService;
import com.bookstore.utimate.domain.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping (value = "/livros")
public class LivroResource {

    @Autowired
    private LivroService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findBuId
            (@PathVariable Integer id) {
            Livro obj = service.findById(id);
            return ResponseEntity.ok().body(obj);

    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll
            (@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
            List<Livro> list = service.findAll(id_cat);
            List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
            return ResponseEntity.ok().body(listDTO);

    }

    @PostMapping
    public ResponseEntity<Livro> create
            (@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,@Valid @RequestBody Livro obj) {
             Livro newObj = service.create(id_cat, obj);
             URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
             return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update
            (@PathVariable Integer id,@Valid @RequestBody Livro obj){
            Livro newObj = service.update (id,obj);
            return ResponseEntity.ok().body(newObj);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Livro> updatePatch
            (@PathVariable Integer id,@Valid @RequestBody Livro obj) {
             Livro newObj = service.update(id, obj);
             return ResponseEntity.ok().body(newObj);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete
            (@PathVariable Integer id) {
            service.delete(id);
            return ResponseEntity.noContent().build();

    }

}