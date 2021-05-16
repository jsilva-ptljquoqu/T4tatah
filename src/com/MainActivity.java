package com.example.hw2_callme;

import android.R;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //--------------------------------------------------------
    public void saveUserPass(View view) {
    	EditText editText_username = (EditText) findViewById(R.id.getUsername);
    	String username = editText_username.getText().toString();

    	EditText editText_password = (EditText) findViewById(R.id.getPassword);
    	String password = editText_password.getText().toString();
    	Context context=getActivity();
		  SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.sharedPreferences),Context.MODE_PRIVATE);
		  SharedPreferences.Editor editor = sharedPref.edit();
		  editor.putString(getString(R.string.username), username); 
		  editor.putString(getString(R.string.password), password); 
		  editor.commit();
		  
		 Intent intent = new Intent(this, Callpan_activity.class);
		 startActivity(intent);

		 
		}
	  
	  public Activity getActivity(){
		  return this;
	  }
}
