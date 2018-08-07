package com.example.triage;

import backend.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SetTreatedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_treated);
		setTitle("Set Patient Treated");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_treated, menu);
		return true;
	}
	/**
	 * Sets the given patient as treated.
	 * @param view
	 */
	public void setTreated(View view){
		
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
		try{
		EditText currentTimeText = (EditText) findViewById(R.id.timeEditView);
		String currentTime = currentTimeText.getText().toString();
		String[] checkTimeformat = currentTime.split(":");
		
		if ((checkTimeformat.length) != 3){
			int k = 1/0;
		}
		nurse.updatePatientTreated(healthCardNum, currentTime);
		intent = new Intent (this, TriageActivity.class);
		
		intent.putExtra("nurseKey", nurse);
		intent.putExtra("healthCardNumKey", healthCardNum);
		startActivity(intent);
		
		
		}catch( ArithmeticException ex ) {
			TextView msg=(TextView)findViewById(R.id.inviald); 
    	    msg.setText("Invaild Input.");
    	    }
	
	}

}
