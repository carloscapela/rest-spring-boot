package br.com.restfullspringboot.mapper.custom;

import br.com.restfullspringboot.data.vo.v1.PersonVO;
import br.com.restfullspringboot.models.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    //private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    public PersonVO convertEntityToVo(Person person) {
        PersonVO vo = new PersonVO();
        vo.setKey(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setBrithDay(new Date());
        vo.setAddress(person.getAddress());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVoToEntity(PersonVO person) {
        Person entity = new Person();
        entity.setId(person.getKey());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        //vo.setBrithDay(new Date());
        entity.setAddress(person.getAddress());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;
    }
}
