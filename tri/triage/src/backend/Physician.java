package backend;

import java.io.Serializable;
import java.util.List;

/**
 * A representation of a Physician class
 * @author Amil
 */


public class Physician extends Person implements Serializable{

	private static final long serialVersionUID = 1L;
	private String prescription;
	private EmergencyRoom ER;
	private Patient patient;
	
	/**
	 * Creates a Physician with a name
	 * @param name
	 */
	public Physician(String name, EmergencyRoom ER) {
		super(name);
		this.ER = ER;
		ER.getEmergencyRoom();
	}
	
	/**
	 * Allows this given Physician to extract a patient from the Emergency room
	 * with a given health card number.
	 * @param healthCardNum
	 */
	public Patient getPatient(String healthCardNum){
		this.patient = this.ER.getPatient(healthCardNum);
		return this.patient;
	}
	
	/**
	 * Allows this Physician to write the prescription in the form [name, instructions]
	 * and then saves it directly to the given patient's Report.
	 * @param preInfo
	 */
	public void setPrescription(String preInfo, String instructions, 
			String healthCardNum, String currentTime){
		this.prescription = preInfo +" "+ instructions;
		patient = this.ER.getPatient(healthCardNum);
		patient.getCurrentReport().updatePrescription(currentTime, this.prescription);
	}
	
	/**
	 * Allows patient to save the current report using the patient's 
	 * health card number
	 * @param healthCardNum
	 */
	public void saveReport(String healthCardNum){
		
		this.ER.getFileManager().wirtenewReport(healthCardNum,
				getPatient(healthCardNum).
				getCurrentReport().toString());
		this.ER.updateEmergencyRoom();
	}

	/**
	 * Return the entire history of the physician by showing them all the 
	 * reports
	 * @param healthCardNum
	 * @return
	 */
	public String viewPatientReports(String healthCardNum){
		return getPatient(healthCardNum).viewallData();
	}
	
	/**
	 * Returns a List of patient's healthCardNumbers which are currently in
	 * this particular Emergency Room.
	 * @return
	 */
	public List<String> viewAllPatientList(){
		return this.ER.getAllPatientList();
	}

	}
