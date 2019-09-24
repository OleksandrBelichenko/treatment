package hospital.model.work;

import hospital.model.assigments.Assigments;
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
public class Work implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Assigments.class)
    @JoinColumn(name = "assigmentsId", nullable = false)
    private Assigments assigments;

    @Column(nullable = false)
    private Double income;

    @Column(nullable = false)
    private Double consumption;

    @Column(nullable = false)
    private Double profit;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Assigments getAssigments() {
        return assigments;
    }

    public void setAssigments(Assigments assigments) {
        this.assigments = assigments;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double income, Double consumption) {
        this.profit = income - consumption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigments, income, consumption, profit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Work)) {return false;}

        Work work = (Work) o;

        return new EqualsBuilder()
                .append(id, work.id)
                .append(assigments, work.assigments)
                .append(income, work.income)
                .append(consumption, work.consumption)
                .append(profit, work.profit)
                .isEquals();
    }

}
