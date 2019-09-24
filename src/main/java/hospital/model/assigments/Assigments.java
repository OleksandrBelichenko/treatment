package hospital.model.assigments;

import hospital.model.common.CommonEntity;
import hospital.model.doctor.Doctor;
import hospital.model.material.Material;
import hospital.model.medication.Medication;
import hospital.model.patient.Patient;
import hospital.model.procedure.Procedure;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Oleksandr Belichenko
 */
@Entity
@ToString
public class Assigments implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @ManyToOne(targetEntity = Material.class)
    @JoinColumn(name = "materialId", nullable = false)
    private Material material;

    @ManyToOne(targetEntity = Procedure.class)
    @JoinColumn(name = "procedureId", nullable = false)
    private Procedure procedure;

    @ManyToOne(targetEntity = Medication.class)
    @JoinColumn(name = "medicationId", nullable = false)
    private Medication medication;

    @Column(nullable = false, unique = true)
    private String date;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, patient, procedure, date, material, medication);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Assigments)) {return false;}

        Assigments assigments = (Assigments) o;

        return new EqualsBuilder()
                .append(id, assigments.id)
                .append(doctor, assigments.doctor)
                .append(patient, assigments.patient)
                .append(material, assigments.material)
                .append(medication, assigments.medication)
                .append(procedure, assigments.procedure)
                .append(date, assigments.date)
                .isEquals();
    }
}
