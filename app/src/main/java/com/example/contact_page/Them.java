package com.example.contact_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Them extends AppCompatActivity {

    EditText txtid,txtname;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        txtid = (EditText) findViewById(R.id.txtma);
        txtname = (EditText) findViewById(R.id.txtten);
        btnsave = (Button) findViewById(R.id.btnLuu);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                int id = Integer.parseInt(txtid.getText().toString());
                String name = txtname.getText().toString();
                Person person = new Person(id,name);
                bundle.putSerializable("per",person);
                intent.putExtra("DATA",bundle);
                setResult(MainActivity.SAVE_NEW_EMP,intent);
                finish();
            }
        });
    }
}