# Tugas Kecil II IF2211 Strategi Algoritma

## Algoritma Decrease and Conquer (Topological Sort)
Penerapan Algoritma Decrease and Conquer akan digunakan untuk Penyusunan Rencana Kuliah dengan Topological Sort. Pada Implementasi program yang telah dibuat, program akan membuat suatu matriks Adjacency yang ukurannya n-nya sama dengan jumlah simpul (matakuliah) yang ada pada file soal. Matriks Adjacency tersebut akan menggambarkan keterkaitan antara setiap simpul yang ada. Kemudian dari matriks Adjacency tersebut, kita dapat memperoleh derajat untuk setiap simpul. Berdasarkan prinsip Algoritma Decrease and Conquer, akan diambil sebuah simpul dengan derajat 0 dan dimasukkan ke dalam array dari solusi. Simpul tersebut akan dikeluarkan dari persoalan dan akan diproses lagi pencariannya untuk simpul - simpul yang lain, yang memiliki derajat tidak 0. Setelah penghapusan suatu simpul, akan ada pembaruan nilai pada matriks Adjacency dan derajat untuk setiap simpul. Proses pencarian berhenti dilakukan ketika semua simpul sudah dikeluarkan dari persoalan (semua matakuliah sudah terurut sesuai dengan semesternya) atau tidak ada simpul yang dapat dikeluarkan lagi.

## Requirement Program
1. Java Runtime Environment (JRE)
2. Java Development Kit (JDK)

## Cara Menggunakan Program
1. Program ini dapat di run melalui command prompt ataupun IDE. Program dapat dijalankan dengan meng-compile source terlebih dahulu atau langsung menggunakan command prompt pada directory bin, menggunakan command "java Main13519097".
2. Ketika program berhasil dijalankan. Program akan meminta input alamat file soal yang akan dibaca.
Contoh alamat file soal yang dapat diinputkan : Contoh1.txt atau /d:/Kuliah/Stima/Tucil2/fix/test/Contoh1.txt
3. Kemudian program akan memproses persoalam dan akan ditampilkan urutan daftar kuliah yang dapat diambil berdasarkan semesternya.

## Author
Nama    : Nabila Hannania
NIM     : 13519097
Kelas   : K02