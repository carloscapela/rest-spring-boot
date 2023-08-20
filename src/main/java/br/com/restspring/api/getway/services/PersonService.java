package br.com.restspring.api.getway.services;

import br.com.restspring.api.getway.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("Buscando pessoa.");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Carlos");
        person.setLastName("Capela");
        person.setAddress("Alameida Marabaixo");
        person.setGender("Masculino");

        return person;
    }

    public List<Person> findAll() {
        logger.info("Buscando pessoas List.");
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Criando pessoa.");
        return person;
    }

    public Person update(Person person) {
        logger.info("Atualizando pessoa.");
        return person;
    }

    public void delete(String id) {
        logger.info("Apagando pessoa.");
    }

    private Person mockPerson(int id) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + id);
        person.setLastName("Person lastName " + id);
        person.setAddress("Address " + id);
        person.setGender("Masculino" + id);

        return person;
    }
}
