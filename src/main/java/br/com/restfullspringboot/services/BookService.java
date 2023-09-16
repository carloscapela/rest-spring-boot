package br.com.restfullspringboot.services;

import br.com.restfullspringboot.data.vo.v1.BookVO;
import br.com.restfullspringboot.models.Book;
import br.com.restfullspringboot.repositories.BookRepository;
import br.com.restfullspringboot.controllers.BookController;
import br.com.restfullspringboot.controllers.PersonController;
import br.com.restfullspringboot.exceptions.ResourceNotFoundException;
import br.com.restfullspringboot.mapper.DozerMapper;
import br.com.restfullspringboot.mapper.custom.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
    private final Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    BookRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<BookVO> findAll() {
        logger.info("Buscando livros List.");
        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books.stream()
                .forEach(p -> p.add(
                        linkTo(
                                methodOn(PersonController.class).findById(p.getKey())
                        ).withSelfRel()
                ));
        return books;
    }

    public BookVO findById(Long id) {
        logger.info("Buscando livro.");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(
                linkTo(
                        methodOn(PersonController.class).findById(id)
                ).withSelfRel()
        );
        return vo;
    }

    public BookVO create(BookVO book) {
        logger.info("Criando livro.");

        var entity = DozerMapper.parseObject(book, Book.class);

        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(
               linkTo(
                        methodOn(PersonController.class).findById(vo.getKey())
               ).withSelfRel()
        );

        return vo;
    }

    public BookVO update(BookVO bookVO) {
        logger.info("Atualizando livro.");

        var entity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        entity.setTitle(bookVO.getTitle());
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunch_date(bookVO.getLaunch_date());
        entity.setPrice(bookVO.getPrice());

        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(
                linkTo(
                        methodOn(BookController.class).findById(vo.getKey())
                ).withSelfRel()
        );
        return vo;
    }

    public void delete(Long id) {
        Book person = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        repository.delete(person);
    }
}
