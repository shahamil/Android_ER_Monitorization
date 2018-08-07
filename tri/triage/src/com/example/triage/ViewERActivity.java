package com.example.triage;

import java.util.ArrayList;
import java.util.List;

import backend.Nurse;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewERActivity extends ListActivity {
	
	private List <String> patients;
	private List <String> newPatList = new ArrayList<String>(); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_view_er);
		setTitle("Patients in Emergency Room");
		Intent intent = getIntent();
		patients = intent.getStringArrayListExtra("lists");
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		
		for (String x : patients){
			String patName = nurse.getPatient(x).getName();
			String patDOB = nurse.getPatient(x).getDOB();
			String newString = patName + "  " + patDOB + "  " + x;
			newPatList.add(newString);
		}
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newPatList));

	
	}
	/**
	 * Allows user to select(click) on a given patient and goes to the
	 * TriageAvtivity
	 */
	public void onListItemClick(ListView parent, View view, int position, long id){
		Intent intent = getIntent();
		Nurse nurse = (Nurse) intent.getSerializableExtra("nurseKey");
		intent = new Intent(this, TriageActivity.class);
		intent.putExtra("nurseKey", nurse);
		intent.putExtra("healthCardNumKey", patients.get(position));
		startActivity(intent);
		
	}

}
