//Test git
package com.example.hitcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText edtSo1, edtSo2, edtKetQua;
    private Button btnCong, btnTru, btnNhan, btnChia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AnhXa();


        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tru_onClick();
            }
        });


        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
    }

    // Hàm ánh xạ View
    private void AnhXa() {
        edtSo1 = (EditText) findViewById(R.id.edt_so1);
        edtSo2 = (EditText) findViewById(R.id.edt_so2);
        edtKetQua = (EditText) findViewById(R.id.edt_ketqua);
        btnCong = (Button) findViewById(R.id.btn_cong);
        btnTru = (Button) findViewById(R.id.btn_tru);
        btnNhan = (Button) findViewById(R.id.btn_nhan);
        btnChia = (Button) findViewById(R.id.btn_chia);
    }


    public void TinhTong(View view) {
        try {
            if (edtSo1.getText().toString().isEmpty() || edtSo2.getText().toString().isEmpty()) {
                throw new Exception("Chưa nhập số");
            }
            double so1 = Double.parseDouble(edtSo1.getText().toString());
            double so2 = Double.parseDouble(edtSo2.getText().toString());
            double tong = so1 + so2;
            edtKetQua.setText(String.valueOf(tong));
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Hàm xử lý logic Phép Trừ (được gọi từ Anonymous Listener bên trên)
    private void Tru_onClick() {
        try {
            if (edtSo1.getText().toString().isEmpty() || edtSo2.getText().toString().isEmpty()) {
                throw new Exception("Chưa nhập số");
            }
            double so1 = Double.parseDouble(edtSo1.getText().toString());
            double so2 = Double.parseDouble(edtSo2.getText().toString());
            double hieu = so1 - so2;
            edtKetQua.setText(String.valueOf(hieu));
        } catch (Exception ex) {Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // --- KỸ THUẬT 2: Activity as Listener (Xử lý thông qua Interface implements bên trên) ---
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_nhan) {
            Nhan_onClick();
        } else if (id == R.id.btn_chia) {
            Chia_onClick();
        }
    }

    // Hàm xử lý phép Nhân
    private void Nhan_onClick() {
        try {
            if (edtSo1.getText().toString().isEmpty() || edtSo2.getText().toString().isEmpty()) {
                throw new Exception("Chưa nhập số");
            }
            double so1 = Double.parseDouble(edtSo1.getText().toString());
            double so2 = Double.parseDouble(edtSo2.getText().toString());
            double tich = so1 * so2;
            edtKetQua.setText(String.valueOf(tich));
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Hàm xử lý phép Chia
    private void Chia_onClick() {
        try {
            if (edtSo1.getText().toString().isEmpty() || edtSo2.getText().toString().isEmpty()) {
                throw new Exception("Chưa nhập số");
            }
            double so1 = Double.parseDouble(edtSo1.getText().toString());
            double so2 = Double.parseDouble(edtSo2.getText().toString());

            if (so2 == 0) {
                throw new Exception("Không thể chia 1 số cho 0");
            }

            double thuong = so1 / so2;
            edtKetQua.setText(String.valueOf(thuong));
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}