package com.example.triage;

import backend.Nurse;
import backend.Physician;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ViewDataActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_data);
		setTitle("View Patient Reports");
		Nurse nurse = null;
		Physician physician = null;
		Intent intent = getIntent();
		nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		physician = (Physician) intent.getSerializableExtra("physicianKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		
		if(nurse!=null){
			String newString = nurse.getPatient(healthCardNum).viewallData();
			TextView msg=(TextView)findViewById(R.id.viewData); 
			msg.setText(newString);
		}
		else if (physician!=null){
			
			String newString = physician.getPatient(healthCardNum).viewallData();
			TextView msg=(TextView)findViewById(R.id.viewData); 
			msg.setText(newString);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_data, menu);
		return true;
	}

}
