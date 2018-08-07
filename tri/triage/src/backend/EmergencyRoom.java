package backend;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Representation of a EmergencyRoom.
 * @author SameerKhan
 *
 */
public class EmergencyRoom implements Serializable {

	/**
	 * Serial id for EmergencyRoom.
	 */
	private static final long serialVersionUID = 2382141998890427966L;
	
	private Map<String, Patient> patients;
	private List<String> patientsArrivalList = new ArrayList<String>();
	private List<String> allPatients = new ArrayList<String>();
	private Map<Integer, List<String>> criticalPatients 
	= new HashMap<Integer,List<String>>(); 
	private FileManager database;
	
	/**
	 * Creates a EmergencyRoom and initialize critical patient list.
	 */
	public EmergencyRoom(File databaseFileName,File loginFileName){
		
		List<String> zero = new ArrayList<String>();
		List<String> one = new ArrayList<String>();
		List<String> two = new ArrayList<String>();
		List<String> three = new ArrayList<String>();
		List<String> four = new ArrayList<String>();
		
		this.criticalPatients.put(0, zero);
		this.criticalPatients.put(1, one);
		this.criticalPatients.put(2, two);
		this.criticalPatients.put(3, three);
		this.criticalPatients.put(4, four);
		
		this.database = new FileManager(databaseFileName, loginFileName);
	}
	
	/**
	 * get all record data from the file and 
	 * add arrival list and critical list.  
	 */
	public void getEmergencyRoom(){
		
		this.database.readFile();
		this.patients = this.database.getPatients();
		
		for (Map.Entry<String, Patient> entry : this.patients.entrySet()){
			
			String tempKey = entry.getKey();
			Patient tempPatient = entry.getValue();
			this.allPatients.add(tempKey);
			if (!(tempPatient.getCurrentReport().getTreated())){
				
				addPatientCriticalList(tempPatient.getUrgency(),tempKey);
			    addPatientArrivalList(tempKey, tempPatient);
			}		    
		}
	}
	
	/**
	 * Returns FileMangaer object.
	 * @return
	 */
	public FileManager getFileManager(){
		return this.database;
	}
	
	/**
	 * get the patient from patients using healthCardNum and 
	 * Return Patient.
	 * @param healthCardNum
	 * @return
	 */
	public Patient getPatient(String healthCardNum){
		return this.patients.get(healthCardNum);
	}
	
	/**
	 * add healthCardNum to appropriate(score) map in the 
	 * critical patient list.
	 * @param score
	 * @param healthCardNum
	 */
	private void addPatientCriticalList(int score, String healthCardNum){
		List<String> newList = this.criticalPatients.get(score);
		newList.add(healthCardNum);
		this.criticalPatients.put(score, newList);
	}
	
	/**
	 * add healthCardNum to appropriate(inTime) index in the arrival patient list.
	 * @param healthCardNum
	 * @param patient
	 */
	private void addPatientArrivalList(String healthCardNum, Patient patient){
		
		if (this.patientsArrivalList.isEmpty()){
			this.patientsArrivalList.add(healthCardNum);
		}
		else{
			for (int i = 0; i < this.patientsArrivalList.size(); i++){
				
				if (compareInTime( this.getPatient(healthCardNum).getInTime(),
						this.getPatient(this.patientsArrivalList.get(i)).getInTime() )){
					
					this.patientsArrivalList.add(i, healthCardNum);
					break;
				}
			}
			if (!(this.patientsArrivalList.contains(healthCardNum))){
				this.patientsArrivalList.add(healthCardNum);
			}
		}		
	}
	
	/**
	 * Return the list of patients(healthCardNum) according to arrival 
	 * time and Patients that are not treated.
	 * @return
	 */
	public List<String> getArrivalList(){
		return this.patientsArrivalList;
	}
	
	/**
	 * Return the list of patients(healthCardNum) according 
	 * to urgency(score) and are not treated.
	 * @return
	 */
	public List<String> getCriticalList(){
		
		List<String> newPatientList = new ArrayList<String>();
		for (int i = 4; i>=0;i--){
			newPatientList.addAll(this.criticalPatients.get(i));
		}
		return newPatientList;
	}
	/**
	 * Returns the list of all patients in this Emergency Room
	 * @return
	 */
	public List<String> getAllPatientList(){
		return this.allPatients;
	}
	
	/**
	 * add new Patient to this emergencyRoom, and record Patient data. 
	 * @param healthCardNum
	 * @param patientToString
	 * @param reportToString
	 */
	public void addPatientToER(String healthCardNum, String patientToString,
								String reportToString){
		this.database.writeNewPatient(healthCardNum, patientToString, 
										reportToString);
		this.updateEmergencyRoom();
	}
	
	/**
	 * remove Patient form this emergencyRoom and remove stored data.
	 * @param healthCardNum
	 */
	public void removePatient(String healthCardNum){
		this.database.removePatient(healthCardNum);
		this.updateEmergencyRoom();
	}
	
	/**
	 * clear all fields except FileManager.
	 */
	private void clearAll(){
		this.patientsArrivalList.clear();
		this.allPatients.clear();
		this.criticalPatients.get(0).clear();
		this.criticalPatients.get(1).clear();
		this.criticalPatients.get(2).clear();
		this.criticalPatients.get(3).clear();
		this.criticalPatients.get(4).clear();
		this.patients.clear();
	}
	
	/**
	 * read all data.
	 */
	public void updateEmergencyRoom(){
		this.clearAll();
		this.getEmergencyRoom();
	}
	
	/**
	 * compares two time string (HH:MM:SS) to check if p1 is less than p2.
	 * @param p1 time string (HH:MM:SS)
	 * @param p2 time string (HH:MM:SS)
	 * @return
	 */
	private boolean compareInTime(String p1, String p2){
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date p2Time = dateFormat.parse(p2);
			Date p1Time = dateFormat.parse(p1);

			if (p2Time.after(p1Time)) // p1 < p2
			{
			    return true; // time ended.
			}
			else{
				return false;
			}
			
		}catch (ParseException e) {
		     e.printStackTrace();
		     return false;
		}
	}
}
