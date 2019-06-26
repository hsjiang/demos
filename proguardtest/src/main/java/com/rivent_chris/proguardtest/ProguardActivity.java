package com.rivent_chris.proguardtest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rivent_chris.proguardtest.Model.Data;
import com.rivent_chris.proguardtest.Model.User;
import com.rivent_chris.proguardtest.Utils.AppUtil;
import com.rivent_chris.proguardtest.Utils.DateUtil;

public class ProguardActivity extends AppCompatActivity {

    private String mString1 = null;
    public String mString2 = null;
    public static final String S_F_STRING = "s_f_string";
    public static String fString = "fString";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proguard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName = AppUtil.getPackageName(null);
                String versionName = AppUtil.getVersionName(null);
                Toast.makeText(ProguardActivity.this, packageName + "\n " + versionName, Toast.LENGTH_SHORT).show();
            }
        });
        User user = new User();
        user.setName("joe");
        user.setAge(20);
        Data data = new Data(0, "");
        int code = data.getCode();
        String dateString = DateUtil.getDate(System.currentTimeMillis());
        MyTest1();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proguard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void MyTest1() {
        mString1 = fString;
        mString2 = S_F_STRING;
    }
}
