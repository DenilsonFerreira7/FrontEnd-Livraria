package com.bookstore.utimate.Service;

import com.bookstore.utimate.DTO.CategoriaDTO;
import com.bookstore.utimate.Exeptions.ObjectNotFoundException;
import com.bookstore.utimate.Repository.CategoriaRepository;
import com.bookstore.utimate.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException
                ("Objeto nao encontrado id: " + id + ", tipo "+ Categoria.class.getName()));

    }
    public List<Categoria>findAll(){
        return repository.findAll();
    }

    public Categoria create (Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Integer id, CategoriaDTO objDTO) {
        Categoria obj = findById(id);
        obj.setNome(objDTO.getNome());
        obj.setDescricao(objDTO.getDescricao());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new com.bookstore.utimate.Exeptions.DataIntegrityViolationException
                    ("Categoria de livros nao pode ser deletado, há livros associados");

        }


        }


    }

