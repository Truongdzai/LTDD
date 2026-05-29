package com.example.hitcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity2 extends AppCompatActivity {


    private EditText edtHoTen, edtDienThoai;
    private Switch swGioiTinh;
    private RadioGroup rgBangCap;
    private CheckBox cbBongDa, cbNgheNhac, cbXemPhim, cbDuLich;
    private Button btnXacNhan, btnHuy;
    private TextView tvKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        initViews();


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXacNhan();
            }
        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHuy();
            }
        });
    }


    private void initViews() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDienThoai = findViewById(R.id.edtDienThoai);
        swGioiTinh = findViewById(R.id.swGioiTinh);
        rgBangCap = findViewById(R.id.rgBangCap);
        cbBongDa = findViewById(R.id.cbBongDa);
        cbNgheNhac = findViewById(R.id.cbNgheNhac);
        cbXemPhim = findViewById(R.id.cbXemPhim);
        cbDuLich = findViewById(R.id.cbDuLich);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnHuy = findViewById(R.id.btnHuy);
        tvKetQua = findViewById(R.id.tvKetQua);
    }


    private void xuLyXacNhan() {
        String hoTen = edtHoTen.getText().toString().trim();
        String dienThoai = edtDienThoai.getText().toString().trim();


        if (hoTen.isEmpty() || dienThoai.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ Họ tên và Điện thoại!", Toast.LENGTH_SHORT).show();
            return;
        }


        String gioiTinh = swGioiTinh.isChecked() ? "Nam" : "Nữ";


        String bangCap = "";
        int checkedRadioId = rgBangCap.getCheckedRadioButtonId();
        if (checkedRadioId != -1) {
            RadioButton rbSelected = findViewById(checkedRadioId);
            bangCap = rbSelected.getText().toString();
        } else {
            bangCap = "Chưa chọn";
        }


        StringBuilder soThich = new StringBuilder();
        if (cbBongDa.isChecked()) soThich.append(cbBongDa.getText().toString()).append(", ");
        if (cbNgheNhac.isChecked()) soThich.append(cbNgheNhac.getText().toString()).append(", ");
        if (cbXemPhim.isChecked()) soThich.append(cbXemPhim.getText().toString()).append(", ");
        if (cbDuLich.isChecked()) soThich.append(cbDuLich.getText().toString()).append(", ");


        String stringSoThich = soThich.toString();
        if (stringSoThich.endsWith(", ")) {
            stringSoThich = stringSoThich.substring(0, stringSoThich.length() - 2);
        }
        if (stringSoThich.isEmpty()) {
            stringSoThich = "Không có";
        }


        String ketQua = "--- THÔNG TIN ĐÃ NHẬP ---\n" +
                "Họ tên: " + hoTen + "\n" +
                "Điện thoại: " + dienThoai + "\n" +
                "Giới tính: " + gioiTinh + "\n" +
                "Bằng cấp: " + bangCap + "\n" +
                "Sở thích: " + stringSoThich;


        tvKetQua.setText(ketQua);
        tvKetQua.setTextColor(ContextCompat.getColor(this, android.R.color.black));
    }


    private void xuLyHuy() {

        edtHoTen.setText("");
        edtDienThoai.setText("");


        swGioiTinh.setChecked(false);
        rgBangCap.clearCheck();
        cbBongDa.setChecked(false);
        cbNgheNhac.setChecked(false);
        cbXemPhim.setChecked(false);
        cbDuLich.setChecked(false);


        tvKetQua.setText("Hiện kết quả");
        tvKetQua.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));

        Toast.makeText(this, "Đã xóa toàn bộ dữ liệu!", Toast.LENGTH_SHORT).show();
    }
}