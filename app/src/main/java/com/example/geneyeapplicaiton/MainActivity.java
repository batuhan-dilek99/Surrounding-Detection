package com.example.geneyeapplicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    EditText Et1, Et2;
    Button Btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referancing to UI elements-----------------
        Btn = (Button)findViewById(R.id.cmr);
        Et1 = (EditText)findViewById((R.id.Et1));
        Et2 = (EditText)findViewById((R.id.Et2));
        tv = (TextView)findViewById((R.id.tv));
        //-------------------------------------------

        //Start python---------------------------------------------
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("deneme");  //Name of the python script
        //---------------------------------------------------------

        //Overriding button onClick event--------------------------
        Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                PyObject obj = pyobj.callAttr("denemesum", Et1.getText().toString(), Et2.getText().toString());  //Function name from the script, function arg1, function arg2

                tv.setText(obj.toString());
            }
        });
        //---------------------------------------------------------
    }
}