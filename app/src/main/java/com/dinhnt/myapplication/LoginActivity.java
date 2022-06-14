package com.dinhnt.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ánh xạ
        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        CheckBox chkRemember = findViewById(R.id.chkRemember);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        preferences = getSharedPreferences("thongtin", MODE_PRIVATE);

        //check ghi nhớ mật khẩu hay không?
        boolean checkRemember = preferences.getBoolean("isRemember", false);
        chkRemember.setChecked(checkRemember);
        if(checkRemember){
            String user = preferences.getString("username", "");
            String pass = preferences.getString("password", "");
            edtUser.setText(user);
            edtPass.setText(pass);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eUser = edtUser.getText().toString();
                String ePass = edtPass.getText().toString();

                String sUser = preferences.getString("username", "");
                String sPass = preferences.getString("password", "");

                if(eUser.equals(sUser) && ePass.equals(sPass)){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isRemember", chkRemember.isChecked());
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}