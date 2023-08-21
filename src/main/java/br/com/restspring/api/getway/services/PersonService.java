package br.com.restspring.api.getway.services;

import br.com.restspring.api.getway.data.vo.v1.PersonVO;
import br.com.restspring.api.getway.exceptions.ResourceNotFoundException;
import br.com.restspring.api.getway.mapper.DozerMapper;
import br.com.restspring.api.getway.models.Person;
import br.com.restspring.api.getway.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {
        logger.info("Buscando pessoas List.");
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Buscando pessoa.");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Criando pessoa.");

        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(Long id, PersonVO person) {
        logger.info("Atualizando pessoa.");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        repository.delete(person);
    }
}
