tugas praktikum 6
Janji: Saya Nisrina Safinatunnajah dengan NIM 2410093 mengerjakan Tugas Praktikum 6 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin


Program ini adalah implementasi game Flappy Bird sederhana menggunakan Java Swing. Desain program dibuat berbasis OOP. Class App berfungsi sebagai entry point program sekaligus menampilkan menu utama dengan dua tombol Mulai Game dan Keluar. Menu ini akan tampil terlebih dahulu sebelum game dimulai. Ketika tombol mulai ditekan, menu ditutup dan frame game utama akan dibuka.

Logika permainan diatur sepenuhnya oleh class Logic. Class ini mengatur pergerakan burung, mekanik gravitasi, menggerakkan pipa ke kiri, mendeteksi tabrakan, menambah skor, hingga proses restart setelah game over. Logic tidak menggambar apapun, tetapi hanya mengatur data dan status permainan. Class View hanya bertugas menggambar tampilan visual berdasarkan data yang diberikan oleh Logic, seperti background, bird, pipe, score, dan tulisan “GAME OVER”. 

Objek burung direpresentasikan sebagai Player yang menyimpan posisi, ukuran, gambar, dan kecepatannya. Sedangkan objek pipa direpresentasikan dengan class Pipe yang menyimpan posisi, ukuran, gambar, serta flag untuk menandai apakah pipa tersebut sudah dilewati pemain atau belum, sehingga bisa menentukan kapan skor bertambah. Label skor ditampilkan menggunakan JLabel agar skor bisa terlihat real-time di frame game.

Alur game berjalan seperti ini: 
saat game dimulai, burung diam di tengah layar sampai pemain menekan tombol SPACE. Setelah SPACE ditekan, game mulai bergerak dan burung akan naik-turun karena efek gravitasi. Pipa muncul setiap beberapa detik lalu bergerak ke kiri. Pemain harus melewati celah pipa. Skor bertambah setiap kali burung melewati pipa bawah. Game akan berhenti (Game Over) jika burung menyentuh pipa atau jatuh ke bawah layar. Setelah Game Over, teks “GAME OVER” akan tampil di dalam game,  dan pemain dapat menekan tombol R untuk restart agar permainan kembali dari awal dan dapat menekan esc untuk kembali ke menu.


Dokumentasi:


