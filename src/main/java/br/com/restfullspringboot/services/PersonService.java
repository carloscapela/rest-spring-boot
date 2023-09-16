package br.com.restfullspringboot.services;

import br.com.restfullspringboot.controllers.PersonController;
import br.com.restfullspringboot.data.vo.v1.PersonVO;
import br.com.restfullspringboot.exceptions.ResourceNotFoundException;
import br.com.restfullspringboot.mapper.DozerMapper;
import br.com.restfullspringboot.mapper.custom.PersonMapper;
import br.com.restfullspringboot.models.Person;
import br.com.restfullspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("Buscando pessoas List.");
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream()
                .forEach(p -> p.add(
                        WebMvcLinkBuilder.linkTo(
                                methodOn(PersonController.class).findById(p.getKey())
                        ).withSelfRel()
                ));
        return persons;
    }

    public PersonVO findById(Long id) {
        logger.info("Buscando pessoa.");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(
                WebMvcLinkBuilder.linkTo(
                        methodOn(PersonController.class).findById(id)
                ).withSelfRel()
        );
        return vo;
    }

    public PersonVO create(PersonVO person) {
        logger.info("Criando pessoa.");

        var entity = DozerMapper.parseObject(person, Person.class);

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(
               WebMvcLinkBuilder.linkTo(
                        methodOn(PersonController.class).findById(vo.getKey())
               ).withSelfRel()
        );

        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Atualizando pessoa.");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(
                WebMvcLinkBuilder.linkTo(
                        methodOn(PersonController.class).findById(vo.getKey())
                ).withSelfRel()
        );
        return vo;
    }

    public void delete(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        repository.delete(person);
    }
}
