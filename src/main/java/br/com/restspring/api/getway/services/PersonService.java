package br.com.restspring.api.getway.services;

import br.com.restspring.api.getway.exceptions.ResourceNotFoundException;
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

    public Person findById(Long id) {
        logger.info("Buscando pessoa.");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));
    }

    public List<Person> findAll() {
        logger.info("Buscando pessoas List.");
        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Criando pessoa.");
        return repository.save(person);
    }

    public Person update(Long id, Person person) {
        logger.info("Atualizando pessoa.");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada encontrado"));

        repository.delete(person);
    }
}
