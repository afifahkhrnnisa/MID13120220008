package com.example.latihanmid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editNilai, editNilai2, txtHasilPerhitungan;
    private RadioGroup radioGroup;
    private Button btnClear;
    private boolean pilihOperasi = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNilai = findViewById(R.id.editNilai);
        editNilai2 = findViewById(R.id.editNilai2);
        txtHasilPerhitungan = findViewById(R.id.txthasilPerhitungan);
        radioGroup = findViewById(R.id.rgOperasi);
        btnClear = findViewById(R.id.button);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNilai.setText("");
                editNilai2.setText("");
                txtHasilPerhitungan.setText("");
                radioGroup.clearCheck();
                pilihOperasi = false;

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pilihOperasi = true;
                hitung();
            }
        });
    }

    public void hitung() {
        if (!pilihOperasi || editNilai.getText().toString().isEmpty() || editNilai2.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Masukkan nilai untuk kedua bilangan", Toast.LENGTH_SHORT).show();
            return;
        }

        double nilai1 = Double.parseDouble(editNilai.getText().toString());
        double nilai2 = Double.parseDouble(editNilai2.getText().toString());

        if (radioGroup.getCheckedRadioButtonId() == R.id.rbTambah || radioGroup.getCheckedRadioButtonId() == R.id.rbKurang || radioGroup.getCheckedRadioButtonId() == R.id.rbKali) {

            int hasilInt = 0;
            if (radioGroup.getCheckedRadioButtonId() == R.id.rbTambah) {
                hasilInt = (int) (nilai1 + nilai2);
            }
            else if (radioGroup.getCheckedRadioButtonId() == R.id.rbKurang) {
                hasilInt = (int) (nilai1 - nilai2);
            }
            else if (radioGroup.getCheckedRadioButtonId() == R.id.rbKali) {
                hasilInt = (int) (nilai1 * nilai2);
            }
            txtHasilPerhitungan.setText(String.valueOf(hasilInt));
        }
        else if (radioGroup.getCheckedRadioButtonId() == R.id.rbBagi) {

            if (nilai2 == 0) {
                txtHasilPerhitungan.setText("Tidak Terdefinisi");
                return;
            }
            double hasilBagi = nilai1 / nilai2;
            txtHasilPerhitungan.setText(String.valueOf(hasilBagi));
        }
    }
}


