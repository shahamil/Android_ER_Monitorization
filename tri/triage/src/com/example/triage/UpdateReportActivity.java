package com.example.triage;

import backend.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateReportActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_report);
		setTitle("Update Report");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_report, menu);
		return true;
	}
	/**
	 * Updates a given patients report by getting the inputed values by the
	 * nurse.
	 * @param view
	 */
	public void updateReportAndSave(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		intent = new Intent (this, TriageActivity.class);
		
		try{
		
    	
			EditText currentTimeText = (EditText) findViewById(R.id.currentTime_editText);
			String currentTime = currentTimeText.getText().toString();
			String[] checkTimeformat = currentTime.split(":");
			
			if ((checkTimeformat.length) != 3){
				int k = 1/0;
			}
			
			
			EditText temperatureText = (EditText) findViewById(R.id.temperature_editText);
			String temperature  = temperatureText.getText().toString();
			if ((temperatureText.length() > 0)){
				double temperatureDouble = Double.parseDouble(temperature);
				nurse.updatePatientVitals(healthCardNum, currentTime, "Temperature",  temperatureDouble);
			}
			
			
			EditText heartRateText = (EditText) findViewById(R.id.heartRate_editText);
			String heartRate = heartRateText.getText().toString();
			if ((heartRateText.length() > 0)){
				double heartRateDouble = Double.parseDouble(heartRate);
				nurse.updatePatientVitals(healthCardNum, currentTime, "HeartRate",  heartRateDouble);
			}
			
			
			EditText diastolicText = (EditText) findViewById(R.id.diastolic_editText);
			String diastolic = diastolicText.getText().toString();
			if (diastolicText.length() > 0){
				
				double diastolicDouble = Double.parseDouble(diastolic);
				nurse.updatePatientVitals(healthCardNum, currentTime, "Diastolic",  diastolicDouble);
			}
			
    	
			EditText systolicText = (EditText) findViewById(R.id.systolic_editText);
			String systolic = systolicText.getText().toString();
			if (systolicText.length() > 0){
				
				double systolicDouble = Double.parseDouble(systolic);
				nurse.updatePatientVitals(healthCardNum, currentTime, "Systolic",  systolicDouble);
			}
			
			EditText symptomsText = (EditText) findViewById(R.id.symptoms_editText);
			String symptoms = symptomsText.getText().toString();
			if (symptomsText.length() > 0){
				
				nurse.updatePatientSymptoms(healthCardNum, currentTime, symptoms);
			}
			
			
			nurse.saveReport(healthCardNum);
			intent.putExtra("nurseKey", nurse);
			intent.putExtra("healthCardNumKey", healthCardNum);
			startActivity(intent);
		}
		
		catch ( ArithmeticException ex ) {
			TextView msg=(TextView)findViewById(R.id.invaildTextView1); 
    	    msg.setText("Invaild Input.");                
        }
	}

}
