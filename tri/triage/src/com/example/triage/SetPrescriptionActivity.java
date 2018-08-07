package com.example.triage;

import backend.Physician;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SetPrescriptionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_prescription);
		setTitle("Prescribe Prescription");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_prescription, menu);
		return true;
	}
	
	/**
	 * Allows this Physician to write/set a prescription for
	 * the given patient
	 * @param view
	 */
	public void setPrescription(View view){
		Intent intent = getIntent();
		Physician physician = (Physician) intent.getSerializableExtra("physicianKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		try{
			
	    	
			EditText currentTimeText = (EditText) findViewById(R.id.currentTimeP_editText);
			String currentTime = currentTimeText.getText().toString();
			String[] checkTimeformat = currentTime.split(":");
			
			if ((checkTimeformat.length) != 3){
				int k = 1/0;
			}
			
			EditText presNameText = (EditText) findViewById(R.id.prescriptionName_editText);
			String preInfo = presNameText.getText().toString();
			
			EditText presInstructionText = (EditText) findViewById(R.id.prescriptionInstructions_EditText);
			String instructions = presInstructionText.getText().toString();
			
			physician.setPrescription(preInfo, instructions, healthCardNum, currentTime);
			physician.saveReport(healthCardNum);	
			
			intent = new Intent (this, PhysicianOptionsActivity.class);
			intent.putExtra("physicianKey", physician);
			intent.putExtra("healthCardNumKey", healthCardNum);
			startActivity(intent);	
		}
		
		catch ( ArithmeticException ex ) {
			TextView msg=(TextView)findViewById(R.id.invaildTextViewp); 
    	    msg.setText("Invaild Input.");                
        }
		

	}

}
