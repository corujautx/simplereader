package com.test.simplereader;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    
    public void onClickLogin(View v)
    {
    	Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
    	startActivity(intent);
    	finish();
    }
}
