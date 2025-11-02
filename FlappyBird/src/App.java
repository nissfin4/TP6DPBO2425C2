import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args){

        // bikin jendela menu awal
        JFrame menuFrame = new JFrame("Menu Flappy Bird");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // kalau ditutup, aplikasi berhenti
        menuFrame.setSize(360, 640); // ukuran frame
        menuFrame.setLocationRelativeTo(null); // posisi di tengah layar
        menuFrame.setResizable(false); // supaya tidak bisa di–resize

        // background gambar awan
        JLabel bg = new JLabel(new ImageIcon(App.class.getResource("/assets/background.png")));
        bg.setLayout(new GridBagLayout()); // supaya tombol ditaruh di tengah layar

        // tombol mulai
        JButton startBtn = new JButton("Mulai Game");
        startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 24)); // ukuran font
        startBtn.setFocusable(false);

        // tombol keluar
        JButton exitBtn = new JButton("Keluar");
        exitBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitBtn.setFocusable(false);

        // panel yang nampung 2 tombol
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false); // panelnya transparan
        btnPanel.setLayout(new GridLayout(2,1,10,10)); // susun tombol jadi 2 baris
        btnPanel.add(startBtn);
        btnPanel.add(exitBtn);

        // taruh tombol di tengah background
        bg.add(btnPanel);
        menuFrame.add(bg);

        // tampilkan frame menu
        menuFrame.setVisible(true);

        startBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                menuFrame.setVisible(false); // benar → jangan dispose
                mulaiGame(menuFrame);
            }
        });


        // saat tombol keluar diklik  aplikasi exit
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // fungsi untuk menjalankan jendela game
    private static void mulaiGame(JFrame menuFrame){
        JFrame frame = new JFrame("Flappy Bird Clone");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        // bikin logic dan view, terus sambungkan
        Logic logic = new Logic();
        View view = new View(logic);
        logic.setView(view);
        logic.setMenuFrame(menuFrame);

        view.addKeyListener(logic);
        // layout border supaya score di atas, dan game canvas di tengah
        frame.setLayout(new BorderLayout());
        frame.add(view, BorderLayout.CENTER);// tampilan game
        frame.add(logic.scoreLabel, BorderLayout.NORTH); // label score di atas

        frame.pack(); // sesuaikan ukuran ke ukuran View
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        view.setFocusable(true);
        view.requestFocusInWindow(); // supaya panel bisa menerima input keyboard
    }
}
