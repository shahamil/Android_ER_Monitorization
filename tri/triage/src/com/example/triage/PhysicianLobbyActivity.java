package com.example.triage;

import java.util.List;

import backend.Nurse;
import backend.Physician;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PhysicianLobbyActivity extends ListActivity {
	
	private List <String> patients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_physician_lobby);
		
		Intent intent = getIntent();
		patients = intent.getStringArrayListExtra("listKey");
		Physician phys = (Physician) intent.getSerializableExtra("physicianKey");
		String title = "Physician: " + phys.getName();
		setTitle(title);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				patients));

	}

	/**
	 * Allows Physician to click on a particular patient which goes to the
	 * PhysicianOptionsActivity
	 */
	public void onListItemClick(ListView parent, View view, int position, long id){
		Intent intent = getIntent();
		Physician phys = (Physician) intent.getSerializableExtra("physicianKey");
		intent = new Intent(this, PhysicianOptionsActivity.class);
		intent.putExtra("physicianKey", phys);
		intent.putExtra("healthCardNumKey", patients.get(position));
		startActivity(intent);
		
	}

}
