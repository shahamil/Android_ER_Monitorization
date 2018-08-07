package com.example.triage;

import backend.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


public class TriageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_triage);
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		String title = nurse.getPatient(healthCardNum).getName();
		setTitle("Patient: " + title);
		
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.triage, menu);
		return true;
	}
	/**
	 * Allows Nurse to view a patient's report
	 * @param view
	 */
	public void viewReport(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		intent = new Intent (this, ViewDataActivity.class);
		intent.putExtra("nurseKey", nurse);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);
		
	}
	/**
	 * Allows Nurse to update vital signs(blood pressure, heart rate, 
	 * temperature) and symptoms of a patient.
	 * @param view
	 */
	public void updateReport(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		intent = new Intent (this, UpdateReportActivity.class);
		intent.putExtra("nurseKey", nurse);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);
	}
	/**
	 * Allows Nurse to remove the given patient from the Emergency Room and
	 * also removes all patient records from database.txt
	 * @param view
	 */
	public void removePatient(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
	    nurse.checkOutPatient(healthCardNum);
		intent = new Intent (this, LobbyActivity.class);
		intent.putExtra("nurseKey", nurse);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);	 	
	}
	
	/**
	 * Sets time and date of when patient was seen by doctor.
	 * @param view
	 */
	public void setSeenByTimeAndDate(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		intent = new Intent (this, SeenByDoctorActivity.class);
		intent.putExtra("nurseKey", nurse);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);
		
	}
	
	/**
	 * Sets the given patient as treated.
	 */
	public void setTreated(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		intent = new Intent (this, SetTreatedActivity.class);
		intent.putExtra("nurseKey", nurse);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);
		
	}
	
	
}
