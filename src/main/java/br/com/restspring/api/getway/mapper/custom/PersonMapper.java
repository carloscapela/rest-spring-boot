package br.com.restspring.api.getway.mapper.custom;

import br.com.restspring.api.getway.data.vo.v2.PersonVOV2;
import br.com.restspring.api.getway.models.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    //private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    public PersonVOV2 convertEntityToVo(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setBrithDay(new Date());
        vo.setAddress(person.getAddress());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        //vo.setBrithDay(new Date());
        entity.setAddress(person.getAddress());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;
    }
}
