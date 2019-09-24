package hospital.model.medication;

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
public class Medication implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Medication)) {return false;}

        Medication medication = (Medication) o;

        return new EqualsBuilder()
                .append(id, medication.id)
                .append(description, medication.description)
                .append(name, medication.name)
                .append(price, medication.price)
                .isEquals();
    }
}
