package com.example.ka.internalstorage_edu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText edtText;
    TextView txtText;
    Button btnSave, btnRead;
    private String simpleFileName = "note.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        txtText = findViewById(R.id.txtText);
        btnRead = findViewById(R.id.btnRead);
        btnSave = findViewById(R.id.btnSve);
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void readFile() throws IOException {
        //mở luồng file
        FileInputStream in  = openFileInput(simpleFileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuffer sb = new StringBuffer();
        String s = null;
        while((s = br.readLine()) != null){
            sb.append(s).append("\n");
        }
        txtText.setText(sb.toString());

    }

    private void saveData() throws IOException {
        String data = edtText.getText().toString();

        try{
            // mở một luồng ghi file
            FileOutputStream out = openFileOutput(simpleFileName,MODE_PRIVATE);
            //ghi dữ liệu

            out.write(data.getBytes());
            out.close();
            Toast.makeText(this, "Saved file", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
