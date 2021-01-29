package com.example.contact_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sua extends AppCompatActivity {

    EditText txtid,txtname;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        txtid = (EditText) findViewById(R.id.txtma);
        txtname = (EditText) findViewById(R.id.txtten);
        btnsave = (Button) findViewById(R.id.btnLuu);

        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        final Person p = (Person) bundle.getSerializable("per");
        txtid.setText(p.getId()+"");
        txtname.setText(p.getName());
        txtid.setEnabled(false);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setName(txtname.getText()+"");
                setResult(MainActivity.SAVE_EDIT_EMP,intent);
                finish();
            }
        });
    }
}