package com.dinhnt.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        EditText edtInput = findViewById(R.id.edtInput);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnShow = findViewById(R.id.btnShow);
        TextView txtResult = findViewById(R.id.txtResult);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String input = edtInput.getText().toString();
                    File path = getCacheDir();
                    File file = new File(path, "mycache.cache");
                    file.createNewFile();
                    FileOutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
                    outputStream.write(input.getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File path = getCacheDir();
                    File file = new File(path, "mycache.cache");
                    Scanner scanner = new Scanner(file);
                    String content = "";
                    while (scanner.hasNext()){
                        content += scanner.nextLine();
                    }
                    txtResult.setText(content);
                    scanner.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}