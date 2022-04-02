package com.bookstore.utimate.Service;

import com.bookstore.utimate.Exeptions.ObjectNotFoundException;
import com.bookstore.utimate.Repository.LivroRepository;
import com.bookstore.utimate.domain.Categoria;
import com.bookstore.utimate.domain.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException
                ("Objeto nao encontrado id: " +id+ "Tipo: " +Livro.class.getName() ));

    }

    public List<Livro> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        return repository.findAllByCategoria(id_cat);
    }

    public Livro create(Integer id_cat, Livro obj) {
        obj.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        obj.setCategoria(cat);
        return repository.save(obj);
    }
}
