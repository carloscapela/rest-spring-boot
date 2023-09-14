package br.com.restspring.api.getway.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"id", "key", "address", "firstName", "lastName", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    //@JsonIgnore
    private String gender;

    public PersonVO() {}

    public Long getKey() { return key; }

    public void setKey(Long key) { this.key = key; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
