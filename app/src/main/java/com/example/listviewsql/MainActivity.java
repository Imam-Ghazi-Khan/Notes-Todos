package com.example.listviewsql;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button add_data;
    EditText add_name;
    ListView userlist;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        listItem = new ArrayList<>();
        add_data = findViewById(R.id.add_data);
        add_name = findViewById(R.id.add_name);
        userlist = findViewById(R.id.users_list);
        viewData();
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = add_name.getText().toString();
                if(!name.equals("") && db.insertData(name)){
                    add_name.setText("");
                    listItem.clear();
                    viewData();
                }
            }
        });
    }
    private void viewData(){
        Cursor cursor = db.viewData();
        if(cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    listItem.add(cursor.getString(1));  //index i is name,index 0 is Id
                }
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);
                userlist.setAdapter(adapter);
        }
    }
}