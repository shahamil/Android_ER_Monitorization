package com.example.triage;



import backend.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PatientFormActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_form);
		setTitle("Patient Form");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_form, menu);
		return true;
		
	}
	/**
	 * Adds Patient to the Emergency Room
	 * @param view
	 */
	public void addPatient(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		
		intent = new Intent (this, LobbyActivity.class);
		
		
		try{
		
			EditText nameText = (EditText) findViewById(R.id.name_editText);
			String name = nameText.getText().toString();
    	
			EditText healthCardNumText = (EditText) findViewById(R.id.healthcardnum_editText);
			String healthCardNum = healthCardNumText.getText().toString();
		
			EditText dobText = (EditText) findViewById(R.id.dateofbirth_editText);
			String dob = dobText.getText().toString();
    	
			EditText ageText = (EditText) findViewById(R.id.age_editText);
			String age = ageText.getText().toString();
			int ageInt =  Integer.parseInt(age);
    	
			EditText currentTimeText = (EditText) findViewById(R.id.currentTime_editText);
			String currentTime = currentTimeText.getText().toString();
			String[] checkTimeformat = currentTime.split(":");
			if ((checkTimeformat.length) != 3){
				int k = 1/0;
				
			}
    	
			EditText temperatureText = (EditText) findViewById(R.id.temperature_editText);
			String temperature = temperatureText.getText().toString();
			double temperatureDouble = Double.parseDouble(temperature);
    	
			EditText heartRateText = (EditText) findViewById(R.id.heartrate_editText);
			String heartRate = heartRateText.getText().toString();
			double heartRateDouble = Double.parseDouble(heartRate);
    	
			EditText diastolicText = (EditText) findViewById(R.id.diastolic_editText);
			String diastolic = diastolicText.getText().toString();
			double diastolicDouble = Double.parseDouble(diastolic);
    	
			EditText systolicText = (EditText) findViewById(R.id.systolic_editText);
			String systolic = systolicText.getText().toString();
			double systolicDouble = Double.parseDouble(systolic);
    	
			EditText symptomsText = (EditText) findViewById(R.id.symptoms_editText);
			String symptoms = symptomsText.getText().toString();
			
			nurse.checkInPatient(healthCardNum, name, dob, ageInt, currentTime,
					temperatureDouble, diastolicDouble, systolicDouble,
					heartRateDouble, symptoms);
			
			intent.putExtra("nurseKey", nurse);
			startActivity(intent);
		}
		
		catch ( ArithmeticException ex ) {
			TextView msg=(TextView)findViewById(R.id.error_textView); 
    	    msg.setText("Invaild Input.");                
        }

		
	}

}
