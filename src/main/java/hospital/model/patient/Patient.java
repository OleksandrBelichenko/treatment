package hospital.model.patient;

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
public class Patient implements CommonEntity<Long> {

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
    private String diagnosis;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, surname, diagnosis);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Patient)) {return false;}

        Patient patient = (Patient) o;

        return new EqualsBuilder()
                .append(id, patient.id)
                .append(name, patient.name)
                .append(secondName, patient.secondName)
                .append(surname, patient.surname)
                .append(diagnosis, patient.diagnosis)
                .isEquals();
    }
}
