package com.example.triage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import backend.EmergencyRoom;
import backend.Nurse;
import backend.Physician;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Allows user to login.
     * @param view
     */
    public void loginUser(View view) {
    	
    	
    	/*
    	 * R stands for "resources", provides references to the resources in the project.
    	 * findViewById returns a View, so need to cast it EditText to be able to 
    	 * access the methods of EditText.
    	 */
    	EditText usernameText = (EditText) findViewById(R.id.username_editText);
    	String username = usernameText.getText().toString();
    	
    	EditText passwordText = (EditText) findViewById(R.id.password_editText);
    	String password = passwordText.getText().toString();
    	
    	File databaseFileName = new File (this.getApplicationContext().getFilesDir(), "database.txt");
    	File loginFileName = new File (this.getApplicationContext().getFilesDir(), "login.txt");
    	
    	EmergencyRoom er = new EmergencyRoom(databaseFileName,loginFileName);
    	 

    	if (er.getFileManager().checkLogin(username, password)){
    		
    		if (er.getFileManager().getType().equalsIgnoreCase("n")){
    		    Intent intent = new Intent(MainActivity.this, LobbyActivity.class);
    			Nurse nurse = new Nurse(er.getFileManager().getUsername(), er);
        		intent.putExtra("nurseKey", nurse);
        		startActivity(intent);
    		}
    		else if (er.getFileManager().getType().equalsIgnoreCase("p")){
    			Intent intent = new Intent(MainActivity.this, PhysicianLobbyActivity.class);
    			Physician physician = new Physician(er.getFileManager().getUsername(), er);
        		List <String> listPatients = physician.viewAllPatientList();
        		intent.putStringArrayListExtra("listKey",(ArrayList<String>)  listPatients);
    			intent.putExtra("physicianKey", physician);
        		startActivity(intent);
    		}

    	}else{
    		TextView msg=(TextView)findViewById(R.id.invalid_textView); 
    	    msg.setText("Invalid username or password");
    	}
    	
    	

    }
    
}
