package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Representation of a FileManager.
 * @author SameerKhan
 *
 */
public class FileManager implements Serializable{
	
	/**
	 * Serial id for FileManager.
	 */
	private static final long serialVersionUID = -5034058212730322192L;
	private String patient, allReports, username, password, type;
	private List<String> listOfReports = new ArrayList<String>();
	private Map<String, Patient> patients = new HashMap<String, Patient>();
	private File databaseFileName, loginFileName;
	
	public FileManager(File databaseFileName,File loginFileName){
		this.databaseFileName = databaseFileName;
		this.loginFileName = loginFileName;
		
	}
	
	/**
	 * write a patient to database.txt.  
	 * @param healthCardNum
	 * @param patientToString
	 * @param reportToString
	 */
	public void writeNewPatient(String healthCardNum, String patientToString, String reportToString){
		
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.databaseFileName, true)));
			out.println(healthCardNum+"~"+patientToString+"~"+reportToString);
			out.close();
		}catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	/**
	 * write a new report for patient(healthCardNum) on database.txt
	 * @param healthCardNum
	 * @param newReport
	 */
	public void wirtenewReport(String healthCardNum, String newReport){
		
		List<String> lines = new ArrayList<String>();
		
		try{
			FileReader fileReader = new FileReader(this.databaseFileName);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	
        	String line = null;
        	while ((line = bufferedReader.readLine()) != null) {
        		lines.add(line);
        	}
        	bufferedReader.close();
        	
        	PrintWriter writer = new PrintWriter(this.databaseFileName);
        	writer.print("");
        	
        	int i = 0;
        	for(String o : lines){
        		String[] list = o.split("~");
        		if(list[0].equalsIgnoreCase(healthCardNum)){
        			lines.set(i, o+newReport);
        		}
        		i=i+1;
        	}
        	for (String o : lines){
        		writer.println(o);
        	}
        	writer.close();	
        }catch (IOException e) {
		    System.err.println(e);
		}
	}
	
	/**
	 * read all data form the file and create Patient with currentReport and listofReports,
	 * and add Patient to patients with patient healthCardNum.
	 */
	public void  readFile(){
		this.patients.clear();
		this.listOfReports.clear();
		try{
			//Create object of FileReader
			FileReader inputFile = new FileReader(this.databaseFileName);

			//Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			//Variable to hold the one line data
			String line;

			// Read file line by line and print on the console
			while ((line = bufferReader.readLine()) != null){
				
				String[] info = line.split("~");
				patient = info[1];
				allReports = info[2];
				String[] list = allReports.split("///");
				for (int i = 0; i < list.length; i++){
					listOfReports.add(list[i]);
				}

				Patient newPatient = createPatient();
				this.patients.put(newPatient.getHealthCardNum(), newPatient);
				listOfReports = new ArrayList<String>();
				
			}
			//Close the buffer reader
			bufferReader.close();
		}catch(Exception e){
            	System.out.println("Error while reading file line by line:" 
            			+ e.getMessage());                      
		}
		
	}
	
	/**
	 * remove a patient(healthCardNum) form database.txt.
	 * @param healthCardNum
	 */
	public void removePatient(String healthCardNum){
		
		List<String> lines = new ArrayList<String>();
		
		try{
			FileReader fileReader = new FileReader(this.databaseFileName);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	
        	String line = null;
        	while ((line = bufferedReader.readLine()) != null) {
        		lines.add(line);
        	}
        	bufferedReader.close();
        	PrintWriter writer = new PrintWriter(this.databaseFileName);
        	writer.print("");
        	
        	List<String> tempString = new ArrayList<String>();
        	int i = 0;
        	for(String o : lines){
        		String[] list = o.split("~");
        		
        		if(!(list[0].equalsIgnoreCase(healthCardNum))){
        			
        			tempString.add(o);
        		}
        		
        		i=i+1;
        	}
        	lines = tempString;
        	for (String o : lines){
        		writer.println(o);
        	}
        	writer.close();	
        }catch (IOException e) {
		    System.out.println(e);
		}
		this.patients.remove(healthCardNum);
	}
	
	/**
	 * Create a Patient. 
	 * @return
	 */
	private Patient createPatient(){
		String[] patientInfo = patient.split(",");
		Patient patientObject = new Patient(patientInfo[0],
											patientInfo[1],
											patientInfo[2],
											Integer.parseInt(patientInfo[3]));
		
		patientObject.setCurrentReport(createCurrentReport());
		patientObject.setOldReports(listOfReports);
		List<String> tempList = patientObject.getOldReports();
		String[] reportInfo = tempList.get(0).split(",");
		patientObject.setInTime(reportInfo[0]);
		
		return patientObject;
	}
	
	/**
	 * create a Report.
	 * @return
	 */
	private Report createCurrentReport(){

		String[] reportInfo =listOfReports.get(listOfReports.size() - 1).split(",");
		Report currentReport = new Report(reportInfo[0],
										Double.parseDouble (reportInfo[1]),
										Double.parseDouble (reportInfo[2]),
										Double.parseDouble (reportInfo[3]),
										Double.parseDouble (reportInfo[4]),
										reportInfo[5]); 
		//if patient was set treated.
		if (Boolean.parseBoolean(reportInfo[6])){
			currentReport.updateTreated(reportInfo[0], true);
		}
		//if patient has a outtime
		if (!(reportInfo[7]).equals("---")){
			currentReport.updateOutTime(reportInfo[7]);
		}
		//if patient perscription was set.
		if (!(reportInfo[8]).equals("---")){
			currentReport.updatePrescription(reportInfo[0], reportInfo[8]);
		}
		
		return currentReport;
	}
	
	/**
	 * return true iff username and password are correct.
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkLogin(String username, String password){
		
		this.username = username;
		this.password = password;
		try{
			//Create object of FileReader
			FileReader inputFile = new FileReader(this.loginFileName);

			//Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			//Variable to hold the one line data
			String line;

			// Read file line by line and print on the console
			while ((line = bufferReader.readLine()) != null)   {
				String[] userandpass = line.split(",");
				
				String loginCheck = this.username+"~"+this.password;
				
				if (userandpass[0].equals(loginCheck)){
					bufferReader.close();
					this.type = userandpass[1];
					return true;
				}
			}
			//Close the buffer reader
			bufferReader.close();
		}catch(Exception e){
            	System.out.println("Error while reading file line by line:" 
            			+ e.getMessage());                      
		}
		return false;
	}
	
	/**
	 * return username.
	 * @return
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * Return type of the user (n for nurse and p for physician).
	 * @return
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * return the map of patients read form database.txt.
	 * @return
	 */
	public Map<String, Patient> getPatients(){
		return this.patients;
	}

}
