# BarangBalik

## Deskripsi Aplikasi

BarangBalik merupakan aplikasi Android sederhana untuk mencatat barang yang dipinjam oleh orang lain. Aplikasi ini membantu pengguna mengingat nama barang, nama peminjam, dan status pengembalian barang.

Aplikasi ini dibuat menggunakan Kotlin dan Jetpack Compose dengan menerapkan arsitektur MVVM.

## Identitas 

- Nama: Aura Dwika Medriana
- NIM: 24523174
- Mata Kuliah: PAB
- Kelas: A

## Fitur Aplikasi

1. **Tambah Data Peminjaman** — mencatat nama barang dan nama peminjam.
2. **Status Pengembalian** — menandai barang yang sudah dikembalikan atau belum.
3. **Hapus Data** — menghapus catatan peminjaman yang tidak diperlukan.

Aplikasi juga memiliki validasi agar nama barang dan nama peminjam tidak kosong saat data ditambahkan.

## Teknologi yang Digunakan

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- MVVM
- StateFlow

## Struktur Aplikasi

- `MainActivity.kt`: menampilkan antarmuka aplikasi.
- `LoanItem.kt`: mendefinisikan data peminjaman barang.
- `LoanViewModel.kt`: mengelola data dan perubahan status peminjaman.

## Alur Aplikasi (UDF)

Ketika pengguna menambahkan data, UI mengirimkan event ke ViewModel. ViewModel memeriksa input dan memperbarui state. Setelah state berubah, UI menampilkan daftar peminjaman sesuai data terbaru.

## Penyimpanan Data

Data peminjaman disimpan sementara di memori aplikasi. Data akan hilang ketika aplikasi dimulai ulang karena aplikasi belum menggunakan database.

## Cara Menjalankan Aplikasi

1. Clone atau download repository ini.
2. Buka project menggunakan Android Studio.
3. Tunggu hingga Gradle Sync selesai.
4. Jalankan emulator Android atau hubungkan perangkat Android.
5. Klik tombol Run untuk menjalankan aplikasi.

## Screenshot

<img width="959" height="501" alt="Cuplikan layar 2026-10-29 225735" src="https://github.com/user-attachments/assets/51126565-ea3e-49e8-b081-89962a1cf38f" />
<img width="959" height="503" alt="Cuplikan layar 2026-10-29 225208" src="https://github.com/user-attachments/assets/ca72b403-c391-4ff9-b31b-00a3076d2374" />

