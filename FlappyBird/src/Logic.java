import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


// class ini adalah otak game ngatur gerakan, tabrakan, score, dan menangani input
public class Logic implements ActionListener, KeyListener {

    // ukuran layar game
    int frameWidth = 360;
    int frameHeight = 640;

    // posisi awal dan ukuran player
    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    // parameter pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view; // view untuk gambar ke layar
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;

    // list semua pipa yang aktif di layar
    private ArrayList<Pipe> pipes = new ArrayList<>();


    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1; // gravitasi tarikan kebawah
    int pipeVelocityX = -2;  // pipa gerak ke kiri
    boolean gameStarted = false;
    private boolean gameOver = false;


    // score
    int score = 0;
    JLabel scoreLabel;

    JFrame menuFrame;
    public void setMenuFrame(JFrame menuFrame){
        this.menuFrame = menuFrame;
    }


    // constructor inisialisasi awal game
    public Logic() {
        birdImage = new ImageIcon(getClass().getResource("/assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("/assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("/assets/upperPipe.png")).getImage();

        // label tampilan score
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(new Color(0, 0, 0, 120)); // semi transparan hitam background score
        scoreLabel.setOpaque(true);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // timer spawn pipa setiap 1.5 detik
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameStarted && !gameOver) {
                    placePipes();
                }
            }
        });

        pipesCooldown.start();

        // timer utama
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    // Getter
    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setView(View view) {
        this.view = view;
    }

    // fungsi utama pergerakan game
    public void move() {

        // kalau belum start atau udah gameOver jangan gerak
        if (!gameStarted || gameOver) {
            return;
        }

        // update velocity jatuh player
        player.setvelocityY(player.getvelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getvelocityY());
        player.setPosY(Math.max(player.getPosY(), 0)); // biar ga tembus atas layar

        // kalau nabrak tanah berarti gameover
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver = true;
        }

        // loop per pipa
        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // tabrakan burung dengan pipa
            if (isColliding(player, pipe)) {
                gameOver = true;
            }

            // nambah score ketika burung berhasil lewat pipa bawah
            if (pipe.getImage() == lowerPipeImage && !pipe.isPassed() &&
                    pipe.getPosX() + pipe.getWidth() < player.getPosX()) {

                pipe.setPassed(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
    }


    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4; // jarak antar pipa

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // fungsi tabrakan
    public boolean isColliding(Player p, Pipe pipe) {
        Rectangle r1 = new Rectangle(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
        Rectangle r2 = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        return r1.intersects(r2);
    }

    // reset semua state ke awal
    public void restartGame() {
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setvelocityY(0);
        pipes.clear();
        gameOver = false;
        gameStarted = false;
        score = 0;
        scoreLabel.setText("Score: 0");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) {
            view.repaint();
        }
    }

    // input keyboard SPACE berarti lompat, R berarti restart
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                gameStarted = true;
                player.setvelocityY(-10);
            } else if (!gameOver) {
                player.setvelocityY(-10);
            }
        }


        // Tekan R buat restart setelah gameOver
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && gameOver){
            // tutup jendela game
            Window w = SwingUtilities.getWindowAncestor(view);
            if(w != null){
                w.dispose();
            }

            // munculin menu lagi
            if(menuFrame != null){
                menuFrame.setVisible(true);
            }
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {}
}
