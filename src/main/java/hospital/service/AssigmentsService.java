package hospital.service;

import hospital.model.assigments.Assigments;
import hospital.model.assigments.AssigmentsDao;
import hospital.model.doctor.Doctor;
import hospital.model.material.Material;
import hospital.model.medication.Medication;
import hospital.model.patient.Patient;
import hospital.model.procedure.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Service
public class AssigmentsService {

    final static Logger logger = LoggerFactory.getLogger(AssigmentsService.class);

    @Autowired
    private AssigmentsDao assigmentsDao;
    @Autowired
    private GeneralService generalService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProcedureService procedureService;
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private WorkService workService;

    public void addAssigments(String doctorName, String doctorSecondName, String doctorSurname,
                              String patientName, String patientSecondName, String patientSurname,
                              String materialNames, String procedureName, String medicationNames,
                              String date) {
        Doctor doctor = doctorService.getDoctorsByFIO(doctorName, doctorSecondName, doctorSurname).get(0);
        Patient patient = patientService.getPatientsByFIO(patientName, patientSecondName, patientSurname).get(0);
        Material material = materialService.getMaterialByName(materialNames);
        Procedure procedure = procedureService.getProcedureByName(procedureName);
        Medication medication = medicationService.getMedicationByName(medicationNames);


        Assigments assigments = new Assigments();
        assigments.setDate(date);
        assigments.setDoctor(doctor);
        assigments.setMaterial(material);
        assigments.setMedication(medication);
        assigments.setPatient(patient);
        assigments.setProcedure(procedure);

        logger.info(assigments.toString());

        assigments = assigmentsDao.saveOrUpdate(assigments);
        workService.addWork(assigments);
    }

    public void addAssigment(String doctor, String patient, String materialName, String procedureName,
                             String medicationName, String date) {

        String[] fullName = doctor.split(" ");

        String doctorName = fullName[0];
        String doctorSecondName = fullName[1];
        String doctorSurname = fullName[2];

        fullName = patient.split(" ");

        String patientName = fullName[0];
        String patientSecondName = fullName[1];
        String patientSurname = fullName[2];

        addAssigments(doctorName, doctorSecondName, doctorSurname, patientName, patientSecondName, patientSurname,
                materialName, procedureName, medicationName, date);
    }

    public void updateAssigments(String doctorName, String doctorSecondName, String doctorSurname,
                                 String patientName, String patientSecondName, String patientSurname,
                                 String materialNames, String procedureName, String medicationNames,
                                 String date) {

        Assigments assigments = getAssigments(date);
        if (assigments == null) {
            addAssigments(doctorName, doctorSecondName, doctorSurname, patientName, patientSecondName, patientSurname,
                    materialNames, procedureName, medicationNames, date);
            return;
        }

        Doctor doctor = doctorService.getDoctorsByFIO(doctorName, doctorSecondName, doctorSurname).get(0);
        Patient patient = patientService.getPatientsByFIO(patientName, patientSecondName, patientSurname).get(0);
        Material material = materialService.getMaterialByName(materialNames);
        Procedure procedure = procedureService.getProcedureByName(procedureName);
        Medication medication = medicationService.getMedicationByName(medicationNames);


        if (generalService.isValidStringData(doctor)) {
            assigments.setDoctor(doctor);
        }
        if (generalService.isValidStringData(patient)) {
            assigments.setPatient(patient);
        }
        if (generalService.isValidStringData(material)) {
            assigments.setMaterial(material);
        }
        if (generalService.isValidStringData(procedure)) {
            assigments.setProcedure(procedure);
        }
        if (generalService.isValidStringData(medication)) {
            assigments.setMedication(medication);
        }

        assigmentsDao.updateAssigments(assigments);
    }

    public List<Assigments> getAssigmentsByPatient(String patientName, String patientSecondName, String patientSurname) {
        List<Patient> patients = patientService.getPatientsByFIO(patientName, patientSecondName, patientSurname);
        Patient patient = null;
        if (patients == null || patients.isEmpty()){
            return null;
        }
        patient = patients.get(0);
        logger.info(patient.getId() + "");
        return assigmentsDao.getAssigmentsByPatient(patient);
    }

    public void removeAssigments(String dateTime) {
        assigmentsDao.removeAssigments(getAssigments(dateTime));
    }

    public Assigments getAssigments(String dateTime) {
        return assigmentsDao.getAssigments(dateTime);
    }

    public List<Assigments> getAssigmentsByProcedure(String procedureName) {
        Procedure procedure = procedureService.getProcedureByName(procedureName);
        logger.info(assigmentsDao.getAssigmentsByProcedure(procedure).size() + "");
        return assigmentsDao.getAssigmentsByProcedure(procedure);
    }

    public List<Assigments> getAll(){
        logger.info(assigmentsDao.findAll().get(0).toString());
        return assigmentsDao.findAll();
    }

}
