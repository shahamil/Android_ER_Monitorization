package backend;

import java.io.Serializable;
import java.util.List;

/**
 * A Representation of a Nurse.
 * @author SameerKhan
 *
 */
public class Nurse extends Person implements Serializable {
	/**
	 * Serial id for Nurse.
	 */
	private static final long serialVersionUID = -8735184454651148478L;
	private EmergencyRoom emergencyRoom;
	
	/**
	 * Creates a Nurse with the given name.
	 * @param name
	 * @param emergencyRoom
	 */
	public Nurse(String name, EmergencyRoom emergencyRoom) {
		super(name);
		// TODO Auto-generated constructor stub
		this.emergencyRoom = emergencyRoom;
		this.emergencyRoom.getEmergencyRoom();
	}
	
	/**
	 * View patients(healthCardNum) list by arrival time and not yet been treated.
	 * @return
	 */
	public List<String> viewArrivalList(){
		return this.emergencyRoom.getArrivalList();
	}
	
	/**
	 * View patients(healthCardNum) list by urgency and not yet been treated.
	 * @return
	 */
	public List<String> viewcriticalList(){
		return this.emergencyRoom.getCriticalList();
	}
	
	/**
	 * add a Patient to the emergencyRoom and also add record patient data.
	 * @param healthCardNum
	 * @param name
	 * @param dob (YYYY/MM/DD)
	 * @param age
	 * @param currentTime (HH:MM:SS)
	 * @param temperature
	 * @param diastolic
	 * @param systolic
	 * @param heartRate
	 * @param symptoms
	 */
	public void checkInPatient(String healthCardNum, String name,
								String dob, int age,
								String currentTime, double temperature,
								double diastolic, double systolic,
								double heartRate, String symptoms){
		
		Patient newPatient = new Patient(name, healthCardNum, dob, age);
		Report patientReport = new Report(currentTime, temperature,diastolic,
											systolic,heartRate,symptoms);
		this.emergencyRoom.addPatientToER(healthCardNum, newPatient.toString(), patientReport.toString());
	}
	
	/**
	 * remove a Patient form emergencyRoom and form database.txt.
	 * @param healthCardNum
	 */
	public void checkOutPatient(String healthCardNum){
		this.emergencyRoom.removePatient(healthCardNum);
	}
	
	/**
	 * update patient's vital signs.
	 * @param healthCardNum
	 * @param currentTime
	 * @param vitalName
	 * @param newValue
	 */
	public void updatePatientVitals(String healthCardNum, String currentTime,
									String vitalName, double newValue){
		
		if (vitalName.equalsIgnoreCase("temperature")){
			this.getPatient(healthCardNum).getCurrentReport().
							updateTemperature(currentTime, newValue);
		}
		else if (vitalName.equalsIgnoreCase("diastolic")){
			this.getPatient(healthCardNum).getCurrentReport().
							updateDiastolic(currentTime, newValue);
		}
		else if (vitalName.equalsIgnoreCase("systolic")){
			this.getPatient(healthCardNum).getCurrentReport().
							updateSystolic(currentTime, newValue);
		}
		else if (vitalName.equalsIgnoreCase("heartRate")){
			this.getPatient(healthCardNum).getCurrentReport().
							updateHeartRate(currentTime, newValue);
		}
	}
	
	/**
	 * update patient's symptoms.
	 * @param healthCardNum
	 * @param currentTime
	 * @param newValue
	 */
	public void updatePatientSymptoms(String healthCardNum, String currentTime, String newValue){
		this.getPatient(healthCardNum).getCurrentReport().updateSymptoms(currentTime, newValue);
	}
	
	/**
	 * set patient as treated.
	 * @param healthCardNum
	 * @param currentTime(HH:MM:SS)
	 * @param date (YYYY/MM/DD)
	 */
	public void updatePatientTreated(String healthCardNum, String currentTime){
		this.getPatient(healthCardNum).getCurrentReport().updateTreated(currentTime, true);
		this.saveReport(healthCardNum);
	}
	
	public void setSeenByDoctor(String healthCardNum, String currentTime, String date){
		this.getPatient(healthCardNum).getCurrentReport().updateOutTime(date+"&"+currentTime);
		this.saveReport(healthCardNum);
	}
	
	/**
	 * save collected data for patient by healthCardNum.
	 * @param healthCardNum
	 */
	public void saveReport(String healthCardNum){
		
		this.emergencyRoom.getFileManager().wirtenewReport(healthCardNum,
				this.getPatient(healthCardNum).
				getCurrentReport().toString());
		this.emergencyRoom.updateEmergencyRoom();
	}
	
	/**
	 * View patients data.
	 * @param healthCardNum
	 * @return
	 */
	public String viewPatientData(String healthCardNum){
		return  this.getPatient(healthCardNum).viewallData();
	}
	
	/**
	 * get a patient form the emergencyRoom.
	 * @param healthCardNum
	 * @return
	 */
	public Patient getPatient(String healthCardNum){
		return this.emergencyRoom.getPatient(healthCardNum);
	}
}
