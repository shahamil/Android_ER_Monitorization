package backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A Representation of a Report.
 * @author SameerKhan
 *
 */
public class Report implements Serializable {

	/**
	 * Serial id for Report.
	 */
	private static final long serialVersionUID = 1472292722742665094L;
	
	private String symptoms, currentTime, outTime, prescription ;
	private double temperature, diastolic, systolic, heartRate; 
	private Map<String, String> currentReport;
	private boolean treated;
	
	/**
	 * Creates a Report with a givens:
	 * @param currentTime (HH:MM:SS)
	 * @param temperature
	 * @param diastolic
	 * @param systolic
	 * @param heartRate
	 * @param symptoms
	 */
	public Report (String currentTime,  
					double temperature, double diastolic, 
					double systolic, double heartRate,
					String symptoms){
		
		
		this.currentTime = currentTime;
		this.temperature = temperature;
		this.diastolic = diastolic;
		this.systolic = systolic;
		this.heartRate = heartRate;
		this.symptoms = symptoms;
		this.treated = false;
		this.outTime = "---";
		this.prescription = "---";
		currentReport = new HashMap<String, String>();
		
		//add all vitals and symptoms to currentReport.
		currentReport.put("Currenttime", this.currentTime);
		currentReport.put("Temperature", Double.toString(this.temperature));
		currentReport.put("Diastolic", Double.toString(this.diastolic));
		currentReport.put("Systolic", Double.toString(this.systolic));
		currentReport.put("HeartRate", Double.toString(this.heartRate));
		currentReport.put("Symptoms", this.symptoms);
		currentReport.put("Treated", Boolean.valueOf(this.treated).toString());
		currentReport.put("Outtime", this.outTime);
		currentReport.put("Prescription", this.prescription);		
	}
	
	/**
	 * return currentReport.
	 * @return
	 */
	public Map<String, String> getCurrentReport(){
		return currentReport;
	}

	/**
	 * set currentTime to new currentTime.
	 * @param currentTime
	 */
	public void updateCurrentTime(String currentTime){
		 this.currentTime = currentTime;
		 currentReport.put("Currenttime", this.currentTime); 
	}

	/**
	 * set temperature to new temperature and update currentTime.
	 * @param currentTime
	 * @param temperature
	 */
	public void updateTemperature(String currentTime, double temperature){
		updateCurrentTime(currentTime); 
		this.temperature = temperature;
		 currentReport.put("Temperature", Double.toString(this.temperature)); 
	}
	
	/**
	 * set diastolic to new diastolic and update currentTime.
	 * @param currentTime
	 * @param diastolic
	 */
	public void updateDiastolic(String currentTime,double diastolic){
		updateCurrentTime(currentTime); 
		 this.diastolic = diastolic;
		 currentReport.put("Diastolic", Double.toString(this.diastolic));
	}
	
	/**
	 * set systolic to new systolic and update currentTime.
	 * @param currentTime
	 * @param systolic
	 */
	public void updateSystolic(String currentTime, double systolic){
		updateCurrentTime(currentTime);  
		this.systolic = systolic;
		 currentReport.put("Systolic", Double.toString(this.systolic)); 
	}
	
	/**
	 * set heartRate to new heartRate and update currentTime.
	 * @param currentTime
	 * @param heartRate
	 */
	public void updateHeartRate(String currentTime, double heartRate){
		updateCurrentTime(currentTime);  
		this.heartRate = heartRate;
		 currentReport.put("HeartRate", Double.toString(this.heartRate));		 
	}
	
	/**
	 * set symptoms to new symptoms and update currentTime.
	 * @param currentTime
	 * @param symptoms
	 */
	public void updateSymptoms(String currentTime, String symptoms){
		updateCurrentTime(currentTime);  
		this.symptoms = symptoms;
		 currentReport.put("Symptoms", this.symptoms);		 
	}

	/**
	 * Set treated to new treated and update currentTime.
	 * @param currentTime
	 * @param treated
	 */
	public void updateTreated(String currentTime, boolean treated){
		updateCurrentTime(currentTime); 
		this.treated = treated;
		currentReport.put("Treated", Boolean.valueOf(this.treated).toString());	
	}
	
	/**
	 * set outTime.
	 * @param outTime (YYYY/MM/DD HH:MM:SS)
	 */
	public void updateOutTime(String outTime){
		String[] x = outTime.split("&");
		updateCurrentTime(x[1]);
		this.outTime = outTime;
		currentReport.put("Outtime", this.outTime);			
	}
	
	/**
	 * update prescription and update currentTime.
	 * @param currentTime
	 * @param prescription
	 */
	public void updatePrescription(String currentTime, String prescription){
		updateCurrentTime(currentTime); 
		this.prescription = prescription;
		currentReport.put("Prescription", this.prescription);			
	}
	
	/**
	 * return heartRate value.
	 * @return
	 */
	public double getHeartRate(){
		return this.heartRate;
	}
	
	/**
	 * return temperature value.
	 * @return
	 */
	public double getTemperature(){
		return this.temperature;
	}
	
	/**
	 * return diastolic value.
	 * @return
	 */
	public double getDiastolic(){
		return this.diastolic;
	}
	
	/**
	 * return systolic value.
	 * @return
	 */
	public double getSystolic(){
		return this.systolic;
	}
	
	/**
	 * return treated value.
	 * @return
	 */
	public boolean getTreated(){
		return this.treated;
	}
	
	/**
	 * return currentReportInfo as a string.
	 */
	public String toString(){		
		String msg = currentReport.get("Currenttime")+","
					+currentReport.get("Temperature")+","
					+currentReport.get("Diastolic")+","
					+currentReport.get("Systolic")+","
					+currentReport.get("HeartRate")+","
					+currentReport.get("Symptoms")+","
					+currentReport.get("Treated")+","
					+currentReport.get("Outtime")+","
					+currentReport.get("Prescription")+"///";
		return msg; 
	}	
}



