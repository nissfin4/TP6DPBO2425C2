import java.awt.*;


public class Pipe {

    private int posX;// posisi pipa di sumbu X kanan-kiri
    private int posY;// posisi pipa di sumbu Y atas-bawah
    private int width;// lebar pipa
    private int height;// tinggi pipa
    private Image image;// gambar pipa yang dipakai untuk ditampilkan
    private int velocityX;// kecepatan gerak pipa ke kiri (negatif)
    boolean passed;// penanda apakah burung sudah melewati pipa ini (untuk tambah score)


    public Pipe(int posX,int posY, int width, int height, Image image){
        // set semua nilai properti berdasarkan parameter saat object dibuat
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = 0; // default kecepatan di awal 0 atau belumjalan
        this.passed = false; // di awal belum dilewati burung
    }

    // getter
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImage() { return image; }
    public int getVelocityX() { return velocityX; }
    public boolean isPassed() { return passed; }

    // setter
    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setImage(Image image) { this.image = image; }
    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }
    public void setPassed(boolean passed) { this.passed = passed; }
}
