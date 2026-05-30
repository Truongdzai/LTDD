# HITCApp – Lập Trình Di Động 1

**Học phần:** Lập Trình Di Động 1 (2TC – 15 tiết LT + 30 tiết TH/SV)  
**Tổ bộ môn:** Công Nghệ Phần Mềm  

---

## Cấu trúc dự án

```
app/src/main/
├── java/com/example/hitcapp/
│   ├── MainActivity.java       # Bài 1 – Ứng dụng tính 2 số
│   └── MainActivity2.java      # Bài 2 – Thông tin cá nhân
└── res/
    └── layout/
        ├── activity_main.xml   # Giao diện Bài 1
        └── activity_main2.xml  # Giao diện Bài 2
```

---

## Buổi 03 – View Cơ Bản Trong Android

### Mục tiêu
- Làm quen các View cơ bản: `TextView`, `EditText`, `Button`, `CheckBox`, `RadioButton`, `Switch`, `ImageView`
- Thực hành 4 kỹ thuật bắt sự kiện: OnClick XML, Anonymous Listener, Variable as Listener, Activity as Listener

---

## Bài 1 – Ứng Dụng Tính 2 Số

### Giao diện (`activity_main.xml`)

Dùng `LinearLayout` dọc làm khung chính. Các thành phần gồm:

| ID | Loại View | Mục đích |
|----|-----------|----------|
| `edt_so1` | EditText | Nhập số thứ nhất |
| `edt_so2` | EditText | Nhập số thứ hai |
| `edt_ketqua` | EditText | Hiển thị kết quả (không chỉnh được) |
| `btn_cong` | Button | Thực hiện phép cộng |
| `btn_tru` | Button | Thực hiện phép trừ |
| `btn_nhan` | Button | Thực hiện phép nhân |
| `btn_chia` | Button | Thực hiện phép chia |

**Lưu ý thiết kế:** 4 Button dùng `layout_weight="1"` để chia đều chiều ngang màn hình.

---

### Logic xử lý (`MainActivity.java`)

#### Bước 1 – Khai báo biến

```java
private EditText edtSo1, edtSo2, edtKetQua;
private Button btnCong, btnTru, btnNhan, btnChia;
```

#### Bước 2 – Ánh xạ View

Tạo hàm `AnhXa()` để gắn biến với View trong XML thông qua `findViewById`:

```java
private void AnhXa() {
    edtSo1    = (EditText) findViewById(R.id.edt_so1);
    edtSo2    = (EditText) findViewById(R.id.edt_so2);
    edtKetQua = (EditText) findViewById(R.id.edt_ketqua);
    btnCong   = (Button)   findViewById(R.id.btn_cong);
    btnTru    = (Button)   findViewById(R.id.btn_tru);
    btnNhan   = (Button)   findViewById(R.id.btn_nhan);
    btnChia   = (Button)   findViewById(R.id.btn_chia);
}
```

#### Bước 3 – Bắt sự kiện (4 kỹ thuật)

---

**Kỹ thuật 1 – OnClick XML (Button Cộng)**

Khai báo thẳng trong file XML, không cần code Java:

```xml
<Button
    android:id="@+id/btn_cong"
    android:onClick="TinhTong"
    android:text="CỘNG" />
```

Trong `MainActivity.java` viết hàm có chữ ký `public void TinhTong(View view)`:

```java
public void TinhTong(View view) {
    try {
        if (edtSo1.getText().toString().isEmpty() || edtSo2.getText().toString().isEmpty()) {
            throw new Exception("Chưa nhập số");
        }
        double so1  = Double.parseDouble(edtSo1.getText().toString());
        double so2  = Double.parseDouble(edtSo2.getText().toString());
        double tong = so1 + so2;
        edtKetQua.setText(String.valueOf(tong));
    } catch (Exception ex) {
        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
    }
}
```

---

**Kỹ thuật 2 – Anonymous Listener (Button Trừ)**

Tạo đối tượng `View.OnClickListener` vô danh ngay tại chỗ gán, gọi trong `onCreate`:

```java
btnTru.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Tru_onClick();   // gọi hàm xử lý logic bên dưới
    }
});

private void Tru_onClick() {
    try {
        if (edtSo1.getText().toString().isEmpty() || edtSo2.getText().toString().isEmpty()) {
            throw new Exception("Chưa nhập số");
        }
        double so1  = Double.parseDouble(edtSo1.getText().toString());
        double so2  = Double.parseDouble(edtSo2.getText().toString());
        double hieu = so1 - so2;
        edtKetQua.setText(String.valueOf(hieu));
    } catch (Exception ex) {
        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
    }
}
```

---

**Kỹ thuật 3 – Activity as Listener (Button Nhân & Chia)**

Activity tự implement interface `View.OnClickListener`, rồi gán `this` cho Button:

```java
// Bước 1: khai báo implements ở đầu class
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Bước 2: gán this cho 2 button trong onCreate
    btnNhan.setOnClickListener(this);
    btnChia.setOnClickListener(this);

    // Bước 3: override phương thức onClick bắt buộc
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_nhan) {
            Nhan_onClick();
        } else if (id == R.id.btn_chia) {
            Chia_onClick();
        }
    }
}
```

Hàm xử lý phép Nhân:

```java
private void Nhan_onClick() {
    try {
        if (edtSo1.getText().toString().isEmpty() || edtSo2.getText().toString().isEmpty()) {
            throw new Exception("Chưa nhập số");
        }
        double so1  = Double.parseDouble(edtSo1.getText().toString());
        double so2  = Double.parseDouble(edtSo2.getText().toString());
        double tich = so1 * so2;
        edtKetQua.setText(String.valueOf(tich));
    } catch (Exception ex) {
        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
    }
}
```

Hàm xử lý phép Chia (có kiểm tra chia cho 0):

```java
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
```

---

## Bài 2 – Ứng Dụng Thông Tin Cá Nhân

### Giao diện (`activity_main2.xml`)

Dùng `ScrollView` bọc ngoài để cuộn được khi nội dung dài. Bên trong là `LinearLayout` dọc.

| ID | Loại View | Mục đích |
|----|-----------|----------|
| `imgAvatar` | ImageView | Hiển thị và chọn ảnh từ bộ nhớ |
| `edtHoTen` | EditText | Nhập họ tên |
| `edtDienThoai` | EditText | Nhập số điện thoại |
| `swGioiTinh` | Switch | Chọn giới tính (Nam/Nữ) |
| `rgBangCap` | RadioGroup | Chọn bằng cấp (1 trong 3) |
| `rbCaoDang` | RadioButton | Cao đẳng |
| `rbDaiHoc` | RadioButton | Đại học |
| `rbCaoHoc` | RadioButton | Cao học |
| `cbBongDa` | CheckBox | Sở thích: Bóng đá |
| `cbNgheNhac` | CheckBox | Sở thích: Nghe nhạc |
| `cbXemPhim` | CheckBox | Sở thích: Xem phim |
| `cbDuLich` | CheckBox | Sở thích: Du lịch |
| `btnXacNhan` | Button | Xác nhận và hiển thị kết quả |
| `btnHuy` | Button | Xóa toàn bộ dữ liệu |
| `tvKetQua` | TextView | Hiển thị thông tin đã nhập |

**Lưu ý bố cục CheckBox:** 4 checkbox dùng `layout_width="0dp"` + `layout_weight="1"` để chia đều hàng ngang, tránh bị đẩy xuống dòng:

```xml
<CheckBox
    android:id="@+id/cbBongDa"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="1"
    android:text="Bóng đá"/>
```

---

### Logic xử lý (`MainActivity2.java`)

#### Bước 1 – Khai báo biến

```java
private EditText edtHoTen, edtDienThoai;
private Switch swGioiTinh;
private RadioGroup rgBangCap;
private CheckBox cbBongDa, cbNgheNhac, cbXemPhim, cbDuLich;
private Button btnXacNhan, btnHuy;
private TextView tvKetQua;
private ImageView imgAvatar;

private ActivityResultLauncher<Intent> chonAnhLauncher;
```

#### Bước 2 – Đăng ký launcher chọn ảnh

Phải đăng ký **trước** khi Activity hiển thị (gọi trong `onCreate` trước `setContentView` hoặc ngay sau):

```java
chonAnhLauncher = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri anhDaChon = result.getData().getData();
                imgAvatar.setImageURI(anhDaChon);  // hiển thị ảnh lên ImageView
            }
        }
    }
);
```

#### Bước 3 – Ánh xạ View

```java
private void initViews() {
    edtHoTen     = findViewById(R.id.edtHoTen);
    edtDienThoai = findViewById(R.id.edtDienThoai);
    swGioiTinh   = findViewById(R.id.swGioiTinh);
    rgBangCap    = findViewById(R.id.rgBangCap);
    cbBongDa     = findViewById(R.id.cbBongDa);
    cbNgheNhac   = findViewById(R.id.cbNgheNhac);
    cbXemPhim    = findViewById(R.id.cbXemPhim);
    cbDuLich     = findViewById(R.id.cbDuLich);
    btnXacNhan   = findViewById(R.id.btnXacNhan);
    btnHuy       = findViewById(R.id.btnHuy);
    tvKetQua     = findViewById(R.id.tvKetQua);
    imgAvatar    = findViewById(R.id.imgAvatar);
}
```

#### Bước 4 – Bắt sự kiện nhấn ảnh để mở thư viện

```java
imgAvatar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");          // chỉ lọc file ảnh
        chonAnhLauncher.launch(intent);     // mở thư viện ảnh
    }
});
```

#### Bước 5 – Xử lý nút Xác Nhận

```java
private void xuLyXacNhan() {
    // 1. Lấy họ tên và điện thoại, kiểm tra rỗng
    String hoTen     = edtHoTen.getText().toString().trim();
    String dienThoai = edtDienThoai.getText().toString().trim();
    if (hoTen.isEmpty() || dienThoai.isEmpty()) {
        Toast.makeText(this, "Vui lòng nhập đầy đủ Họ tên và Điện thoại!", Toast.LENGTH_SHORT).show();
        return;
    }

    // 2. Lấy giới tính từ Switch (true = Nam, false = Nữ)
    String gioiTinh = swGioiTinh.isChecked() ? "Nam" : "Nữ";

    // 3. Lấy bằng cấp từ RadioGroup
    String bangCap = "Chưa chọn";
    int checkedId = rgBangCap.getCheckedRadioButtonId();
    if (checkedId != -1) {
        RadioButton rb = findViewById(checkedId);
        bangCap = rb.getText().toString();
    }

    // 4. Gom các sở thích đã tick từ CheckBox
    StringBuilder soThich = new StringBuilder();
    if (cbBongDa.isChecked())   soThich.append(cbBongDa.getText()).append(", ");
    if (cbNgheNhac.isChecked()) soThich.append(cbNgheNhac.getText()).append(", ");
    if (cbXemPhim.isChecked())  soThich.append(cbXemPhim.getText()).append(", ");
    if (cbDuLich.isChecked())   soThich.append(cbDuLich.getText()).append(", ");

    // Xóa dấu ", " thừa cuối chuỗi
    String strSoThich = soThich.toString();
    if (strSoThich.endsWith(", "))
        strSoThich = strSoThich.substring(0, strSoThich.length() - 2);
    if (strSoThich.isEmpty()) strSoThich = "Không có";

    // 5. Hiển thị kết quả
    String ketQua = "--- THÔNG TIN ĐÃ NHẬP ---\n"
            + "Họ tên: "     + hoTen     + "\n"
            + "Điện thoại: " + dienThoai + "\n"
            + "Giới tính: "  + gioiTinh  + "\n"
            + "Bằng cấp: "   + bangCap   + "\n"
            + "Sở thích: "   + strSoThich;
    tvKetQua.setText(ketQua);
    tvKetQua.setTextColor(ContextCompat.getColor(this, android.R.color.black));
}
```

#### Bước 6 – Xử lý nút Hủy

Reset toàn bộ giao diện về trạng thái ban đầu:

```java
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
    imgAvatar.setImageResource(android.R.drawable.ic_menu_gallery);  // reset ảnh
    Toast.makeText(this, "Đã xóa toàn bộ dữ liệu!", Toast.LENGTH_SHORT).show();
}
```

---

## Tóm tắt kỹ thuật sự kiện

| Kỹ thuật | Áp dụng cho | Cách dùng |
|---|---|---|
| **OnClick XML** | Button Cộng | Khai báo `android:onClick="TênHàm"` trong XML, viết hàm `public void TênHàm(View v)` trong Activity |
| **Anonymous Listener** | Button Trừ | `btn.setOnClickListener(new View.OnClickListener() { ... })` ngay trong `onCreate` |
| **Activity as Listener** | Button Nhân, Chia | Class `implements View.OnClickListener`, gán `this`, override `onClick()` |
| **ActivityResultLauncher** | ImageView chọn ảnh | `registerForActivityResult(...)` nhận ảnh trả về từ thư viện hệ thống |
