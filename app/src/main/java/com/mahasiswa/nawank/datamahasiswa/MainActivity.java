package com.mahasiswa.nawank.datamahasiswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://bosok.000webhostapp.com/";
    private RadioButton radioSexButton;
    private ProgressDialog progress;

    @BindView(R.id.radioGender) RadioGroup radioGroup;
    @BindView(R.id.editTextNIM) EditText editTextNIM;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextKelas) EditText editTextKelas;

    @OnClick(R.id.buttonSimpan) void simpan() {
        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //mengambil data dari edittext
        String nim = editTextNIM.getText().toString();
        String nama = editTextNama.getText().toString();
        String kelas = editTextKelas.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        // mencari id radio button
        radioSexButton = (RadioButton) findViewById(selectedId);
        String gender = radioSexButton.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.simpan(nim, nama, kelas, gender);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(MainActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.buttonLihat) void lihat() {
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}