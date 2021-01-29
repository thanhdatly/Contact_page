package com.example.contact_page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_EMP = 113;
    public static final int EDIT_EMP = 114;
    public static final int SAVE_NEW_EMP = 115;
    public static final int SAVE_EDIT_EMP = 116;
    ListView lv ;
    int posselected = -1;
    ArrayList<Person> list =new ArrayList<Person>();
    ArrayAdapter<Person> adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lvName);
        list.add(new Person(1,"Tí"));
        list.add(new Person(2,"Tèo"));
        adapter = new ArrayAdapter<Person>(this,R.layout.support_simple_spinner_dropdown_item,list);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posselected = position;
                return false;
            }
        });
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.mnThem:
                doStartNew();
                break;
            case R.id.mnSua:
                doStartEdit();
                break;
            case R.id.mnXoa:
                doDelete();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void doStartNew(){
        Intent intentNew = new Intent(this,Them.class);
        startActivityForResult(intentNew,MainActivity.NEW_EMP);
    }

    public void doStartEdit(){
        Intent intent = new Intent(this,Sua.class);
        Person p =list.get(posselected);
        Bundle bundle = new Bundle();
        bundle.putSerializable("per",p);
        intent.putExtra("DATA",bundle);
        startActivityForResult(intent,MainActivity.EDIT_EMP);

    }
    public void doDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Hỏi xóa");
        builder.setMessage("Muốn xóa thiệt hả");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(posselected);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MainActivity.NEW_EMP:
                if(resultCode==MainActivity.SAVE_NEW_EMP){
                    Bundle bundle = data.getBundleExtra("DATA");
                    Person p = (Person) bundle.getSerializable("per");
                    list.add(p);
                    adapter.notifyDataSetChanged();
                }
                break;
            case MainActivity.EDIT_EMP:
                if(resultCode==MainActivity.SAVE_EDIT_EMP){
                    Bundle bundle = data.getBundleExtra("DATA");
                    Person p = (Person) bundle.getSerializable("per");
                    list.set(posselected,p);
                }
                break;
        }
    }
}