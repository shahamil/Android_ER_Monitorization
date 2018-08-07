package backend;

import java.io.Serializable;
import java.util.List;

/**
 * A Representation of a Patient.
 * @author SameerKhan
 *
 */
public class Patient extends Person implements Serializable {

	/**
	 * Serial id for Patient.
	 */
	private static final long serialVersionUID = -7654466491742933248L;

	private String healthCardNum, dob, inTime;
	private int age;
	private List<String> oldReports;
	private Report currentReport;
	private int score;
	
	/**
	 * Creates a Patient with a given name, health card number, date of birth and age. 
	 * @param name
	 * @param healthCardNum
	 * @param dob
	 * @param age
	 */
	public Patient(String name, String healthCardNum, String dob, int age) {
		super(name);
		// TODO Auto-generated constructor stub
		this.healthCardNum = healthCardNum;
		this.age = age;
		this.dob = dob;
	}
	
	/**
	 * calculate and return Urgency score for Patient.
	 * @return
	 */
	public int getUrgency(){
		score = 0;
		if (this.age < 2){
			score = score+1;
		}
		if(currentReport.getTemperature() >= 39.0 ){
			score = score+1;
		}
		if(currentReport.getSystolic() >= 140.0 || (currentReport.getDiastolic() >= 90.0 )){
			score = score+1;
		}
		if(currentReport.getHeartRate() >= 100 || currentReport.getHeartRate() <= 50){
			score = score+1;
		}
		return score;
	}
	
	/**
	 * sets the currentReport.
	 * @param currentReport
	 */
	public void setCurrentReport(Report currentReport){
		this.currentReport = currentReport;
	}
	
	/**
	 * set all reports for Patient.
	 * @param oldReports
	 */
	public void setOldReports(List<String> oldReports){
		this.oldReports = oldReports;
	}
	
	/**
	 * set inTime for a patient.
	 * @param inTime
	 */
	public void setInTime(String inTime){
		this.inTime = inTime;
	}
	
	/**
	 * return currentReport for Patient.
	 * @return
	 */
	public Report getCurrentReport(){
		return this.currentReport;
	}
	
	/**
	 * return oldReports for patient.
	 * @return
	 */
	public List<String> getOldReports(){
		return this.oldReports;
	}
	
	/**
	 * return healthCardNum for Patient.
	 * @return
	 */
	public String getHealthCardNum(){
		return this.healthCardNum;
	}
	
	/**
	 * Returns this patient's date of birth
	 * @return dob (Date of Birth)
	 */
	public String getDOB(){
		return this.dob;
	}
	
	/**
	 * return inTime for patient.
	 * @return
	 */
	public String getInTime(){
		return this.inTime;
	}
	
	/**
	 * return all collected data as string.
	 * @return
	 */
	public String viewallData(){
		List<String> list = this.getOldReports();
		String patientInfo = "Name: "+this.getName() +"\n"
							+" HealthCardNumber: "+ this.getHealthCardNum() + "\n"
							+"Arrival Time: "+ this.getInTime()+"\n"
							+" Age: "+ this.age +"\n"
							+" Date of Birth: "+ this.dob + "\n";
		String reports = "";
		int index = 0;
		for (String element : list) {
			String[] info = element.split(",");
			
			String msg = "\n"+"Report"+index+" Update Time: "+ info[0]
						+ "\n"
						+"Temperature: "+ info[1]+ "\n"
						+" Diastolic: "+ info[2] + "\n"
						+" Systolic: "+ info[3] + "\n"
						+" HeartRate: "+ info[4] + "\n"
						+" Symptoms: "+ info[5] + "\n"
						+" Treated: "+ info[6]   + "\n"
						+" Seen By Time: "+ info[7] + "\n"
						+" Prescription: "+ info[8] + "\n";
			reports = reports + msg;
			index++;
		}
		return patientInfo+reports;
		
	}
	/**
	 * return patientInfo as a string.
	 */
	public String toString(){
		String msg = this.getName()+","+this.getHealthCardNum()+","
					+ this.dob+","+this.age;
		return msg;
	}
}
