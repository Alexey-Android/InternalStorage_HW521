package com.example.internalstorage_hw521;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        final EditText mLoginEdTxt = findViewById(R.id.et_login);
        final EditText mPasswordEdTxt = findViewById(R.id.et_password);
        Button mLogin = findViewById(R.id.btn_login);
        Button mRegistration = findViewById(R.id.btn_registration);

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nLogin = mLoginEdTxt.getText().toString();
                final String nPassword = mPasswordEdTxt.getText().toString();

                if (nLogin.equals("") || nPassword.equals("")) {
                    Toast.makeText(MainActivity.this, "Введите логин и пароль", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Логин и пароль введены", Toast.LENGTH_LONG).show();
                }

                String loginFileName = "login";
                String passwordFileName = "password";

                // Создадим файл и откроем поток для записи данных
                FileOutputStream fileOutputStreamLogin = null;
                try {
                    fileOutputStreamLogin = openFileOutput(loginFileName, MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
// Обеспечим переход символьных потока данных к байтовым потокам.
                OutputStreamWriter outputStreamWriterLogin = new OutputStreamWriter(fileOutputStreamLogin);
// Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
                BufferedWriter bwLogin = new BufferedWriter(outputStreamWriterLogin);
// Осуществим запись данных
                try {
                    bwLogin.write(nLogin);
                    //
                } catch (IOException e) {
                    e.printStackTrace();
                }
// закроем поток
                try {
                    bwLogin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Создадим файл и откроем поток для записи данных
                FileOutputStream fileOutputStreamPassword = null;
                try {
                    fileOutputStreamPassword = openFileOutput(passwordFileName, MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
// Обеспечим переход символьных потока данных к байтовым потокам.
                OutputStreamWriter outputStreamWriterPassword = new OutputStreamWriter(fileOutputStreamPassword);
// Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
                BufferedWriter bwPassword = new BufferedWriter(outputStreamWriterPassword);
// Осуществим запись данных
                try {
                    bwPassword.write(nPassword);

                } catch (IOException e) {
                    e.printStackTrace();
                }
// закроем поток
                try {
                    bwPassword.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получим входные байты из файла которых нужно прочесть.
                FileInputStream fileInputStreamLogin = null;
                try {
                    fileInputStreamLogin = openFileInput("login");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
// Декодируем байты в символы
                InputStreamReader inputStreamReaderLogin = new InputStreamReader(fileInputStreamLogin);
// Читаем данные из потока ввода, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
                BufferedReader readerLogin = new BufferedReader(inputStreamReaderLogin);
                try {
                    String lineLogin = readerLogin.readLine();
                    if (lineLogin.equals(mLoginEdTxt.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Логин введен верно", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Логин неверен", Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Получим входные байты из файла которых нужно прочесть.
                FileInputStream fileInputStreamPassword = null;
                try {
                    fileInputStreamPassword = openFileInput("password");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
// Декодируем байты в символы
                InputStreamReader inputStreamReaderPassword = new InputStreamReader(fileInputStreamPassword);
// Читаем данные из потока ввода, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
                BufferedReader readerPassword = new BufferedReader(inputStreamReaderPassword);
                try {
                    String linePassword = readerPassword.readLine();
                    if (linePassword.equals(mPasswordEdTxt.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Пароль введен верно", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Пароль неверен", Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });

    }
}