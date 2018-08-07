package com.example.triage;


import backend.Physician;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class PhysicianOptionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_physician_options);
		setTitle("Options");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.physician_options, menu);
		return true;
	}
	
	/**
	 * Allows this Physican to see a given patient's report
	 * @param view
	 */
	public void viewReport(View view){
		
		Intent intent = getIntent();
		Physician physician = (Physician) intent.getSerializableExtra("physicianKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		intent = new Intent (this, ViewDataActivity.class);
		intent.putExtra("physicianKey", physician);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);
	}
	
	/**
	 * Sets the prescription of a given patient.
	 * @param view
	 */
	public void setPrescription(View view){
		
		Intent intent = getIntent();
		Physician physician = (Physician) intent.getSerializableExtra("physicianKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		intent = new Intent (this, SetPrescriptionActivity.class);
		intent.putExtra("physicianKey", physician);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);		
	}

}
