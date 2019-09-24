package hospital.model.doctor;

import hospital.model.common.CommonEntity;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.Objects;


/**
 * @author Oleksandr Belichenko
 */
@Entity
@ToString
public class Doctor implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String specialization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, surname, specialization);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Doctor)) {return false;}

        Doctor doctor = (Doctor) o;

        return new EqualsBuilder()
                .append(id, doctor.id)
                .append(name, doctor.name)
                .append(secondName, doctor.secondName)
                .append(surname, doctor.surname)
                .append(specialization, doctor.specialization)
                .isEquals();
    }
}
