import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class View extends JPanel{ // class ini bagian yang menggambar atau UI tampilan game di layar
    private Logic logic; // buat ambil data dari logic player, pipe, state game
    int width = 360;  // lebar window
    int height = 640; // tinggi window

    Image background; // gambar background awan nanti ditampilkan disini

    public View(Logic logic){  // constructor, dipanggil sekali waktu View dibuat
        this.logic = logic;// simpan referensi logic biar bisa baca nilai-nilai game
        setPreferredSize(new Dimension(width, height)); // set ukuran panel
        setBackground(Color.cyan); // warna background default kalau gambar belum ke load

        // load background dari folder assets
        background = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();

        setFocusable(true);// supaya panel bisa fokus dan terima input keyboard
        addKeyListener(logic);// keyboard space/R di handle oleh class logic
        requestFocus();// minta fokus keyboard begitu dibuka
    }

    @Override
    public void paintComponent(Graphics g){ // method bawaan swing untuk menggambar
        super.paintComponent(g); // bersihin layar dulu
        draw(g);// panggil method kita sendiri buat gambar objek game
    }

    public void draw(Graphics g){ // method untuk gambar background, player, pipe, game over
        if (background != null) {
            g.drawImage(background, 0, 0, width, height, null); // gambar background full
        }

        Player player = logic.getPlayer(); // ambil player dari logic
        if (player != null){
            g.drawImage(player.getImage(), player.getPosX(),player.getPosY(),
                    player.getWidth(), player.getHeight(), null); // gambar burung
        }

        ArrayList<Pipe> pipes = logic.getPipes(); // ambil daftar pipa dari logic
        if(pipes != null){
            for(int i = 0; i < pipes.size(); i++){ // loop semua pipa
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(),pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null); // gambar pipa 1 per 1
            }

            // cek kondisi kalau udah gameOver maka tampilkan tulisan
            if (logic.isGameOver()) {
                g.setFont(new Font("Arial", Font.BOLD, 32)); // font besar
                g.setColor(Color.WHITE); // warna putih
                g.drawString("GAME OVER", 80, 200);// tulis game over

                g.setFont(new Font("Arial", Font.PLAIN, 18)); // font kecil
                g.drawString("Press R to Restart", 95, 240);// tulisan instruksi restart
                g.drawString("Press ESC to back to Menu", 95, 270);//tulisan untuk balik ke menu pencet esc

            }
        }
    }

}
