package com.example.triage;

import java.util.ArrayList;
import java.util.List;

import backend.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class LobbyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lobby);
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String title = nurse.getName();
		setTitle("Nurse: " + title);
		if (nurse.viewArrivalList().isEmpty()){
    		
			TextView msg=(TextView)findViewById(R.id.emptyER_textView); 
    	    msg.setText("No Patients in Emergency Room. add pateint.");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lobby, menu);
		return true;
	}
	
	/**
	 * Get the list of patients from Emergency Room based on their urgency
	 * @param view
	 */
	public void getCriticalList(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		
		intent = new Intent (this, ViewERActivity.class);
		intent.putExtra("nurseKey", nurse);
		List <String> listPatients = nurse.viewcriticalList();
		intent.putStringArrayListExtra("lists", (ArrayList<String>) listPatients);
		startActivity(intent);
	}
	
	/**
	 * Populates ERActivity by arrival list of patients(healthCardNum)
	 * @param view
	 */
	public void getArrivalList(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		
		intent = new Intent (this, ViewERActivity.class);
		intent.putExtra("nurseKey", nurse);
		List <String> listPatients = nurse.viewArrivalList();
		intent.putStringArrayListExtra("lists", (ArrayList<String>) listPatients);
		startActivity(intent);
	}
	
	/**
	 * Allows Nurse to check in a new patient.
	 * @param view
	 */
	public void checkINPatient(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		
		intent = new Intent (this, PatientFormActivity.class);
		intent.putExtra("nurseKey", nurse);
		
		startActivity(intent);
	}

}
