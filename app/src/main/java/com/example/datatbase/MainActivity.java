package com.example.datatbase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static EditText fn,ln,ma,id;
    private static Button add,view,update,delete;
    database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new database(this);
        fn=(EditText)findViewById(R.id.fn);
        ln=(EditText)findViewById(R.id.ln);
        ma=(EditText)findViewById(R.id.ma);
        id=(EditText)findViewById(R.id.id);
        add=(Button)findViewById(R.id.add);
        view=(Button)findViewById(R.id.view);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);

        add();
        view();
        update();
        delete();
    }

    public void add(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isin = mydb.insert(fn.getText().toString(), ln.getText().toString(), ma.getText().toString());
                if (isin = true)
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void view(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.get();
                if (res.getCount() == 0) {
                    mssg("Error", "No record found");

                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID : "+res.getString(0)+"\n");
                    buffer.append("FIRST NAME : "+res.getString(1)+"\n");
                    buffer.append("LAST NAME : "+res.getString(2)+"\n");
                    buffer.append("MARKS : "+res.getString(3)+"\n\n");
                }
                mssg("Data",buffer.toString());
            }
        });

    }

    public void update(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = mydb.update(id.getText().toString(),fn.getText().toString(), ln.getText().toString(), ma.getText().toString());
                if (isUpdate = true)
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void delete(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows= mydb.delete(id.getText().toString());
                if (deletedrows > 0)
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void mssg(String title, String msg){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
