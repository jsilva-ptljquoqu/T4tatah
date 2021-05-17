package com.example.hw2_callme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CallPanActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callpan);
	}

	public void wakeSomeone(View view) {
		Context context = getActivity();
		SharedPreferences sharedPref = context.getSharedPreferences(
				getString(R.string.sharedPreferences), Context.MODE_PRIVATE);
		String defaultUsserName = getString(R.string.default_username_key);
		String defaultPassword = getString(R.string.default_password_key);
		String username = sharedPref.getString(getString(R.string.username),
				defaultUsserName);
		String password = sharedPref.getString(getString(R.string.password),
				defaultPassword);
		System.out.println("hi");
		new GET(username, password).execute();
	}

	class GET extends AsyncTask<String, String, String> {

		String username, password;
		public GET(String user, String pass) {
			username = user;
			password = pass;
		}
		@Override
		protected String doInBackground(String... arg0) {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("http://wake.huri.ir/call/?user="
					+ username + "&pass=" + password);
			try {
				HttpResponse response = client.execute(httpGet);
				InputStream inputStream = response.getEntity().getContent();
				InputStreamReader reader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(reader);
				return bufferedReader.readLine();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (result.equals("0")) {
				Toast.makeText(getApplicationContext(),
						getString(R.string.no_number_found),
						Toast.LENGTH_SHORT).show();
			} else {
				Uri number = Uri.parse("tel:" + result);
				Intent callIntent = new Intent(Intent.ACTION_CALL, number);
				try {
					startActivity(callIntent);
					
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"Wrong Number",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
	
			
	private Context getActivity() {
		return this;
	}

}
