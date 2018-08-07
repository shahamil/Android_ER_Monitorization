package com.example.triage;

import backend.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SeenByDoctorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seen_by_doctor);
		setTitle("Seen By Doctor");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seen_by_doctor, menu);
		return true;
	}
	
	/**
	 * Sets the date and time of Patient was seen by Doctor.
	 * @param view
	 */
	public void setSeenByTime (View view){
		try{
			
	    	
			EditText currentTimeText = (EditText) findViewById(R.id.seenby_timeedit);
			String currentTime = currentTimeText.getText().toString();
			String[] checkTimeformat = currentTime.split(":");
			
			if ((checkTimeformat.length) != 3){
				int k = 1/0;
			}
			
			EditText currentDateText = (EditText) findViewById(R.id.seenby_dateedit);
			String currentDate = currentDateText.getText().toString();
			
			Intent intent = getIntent();
			Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
			String healthCardNum = (String) intent.getSerializableExtra("healthCardNumKey");
			
			intent = new Intent (this, TriageActivity.class);
			nurse.setSeenByDoctor(healthCardNum, currentTime, currentDate);
			intent.putExtra("nurseKey", nurse);
			intent.putExtra("healthCardNumKey", healthCardNum);
			startActivity(intent);
			
		}catch ( ArithmeticException ex ) {
			TextView msg=(TextView)findViewById(R.id.invaildtextView2); 
    	    msg.setText("Invaild Input.");                
        }
	}

}
