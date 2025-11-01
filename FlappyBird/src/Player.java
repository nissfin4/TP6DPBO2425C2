import java.awt.*;

public class Player { // class ini merepresentasikan objek burung player

    private int posX;// posisi burung secara horizontal kiri atau kanan
    private int posY;// posisi burung secara vertical atas atau bawah
    private int width;// lebar gambar burung
    private int height;// tinggi gambar burung
    private Image image;// file gambar burung yang ditampilkan

    private int velocityY;// kecepatan gerak burung ke atas atau bawah

    // constructor dipanggil sekali saat burung pertama dibuat
    public Player(int posX, int posY, int width, int height, Image image){
        this.posX = posX;// set posisi X awal
        this.posY = posY;// set posisi Y awal
        this.width = width;// set ukuran lebar burung
        this.height = height;// set ukuran tinggi burung
        this.image = image;// set gambar burung

        this.velocityY = -0;// kecepatan awal
    }

    // getter mengambil nilai
    public int getPosX() { return posX; }
    public int getvelocityY(){ return velocityY; }
    public int getPosY() { return posY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImage() { return image; }

    // setter mengubah nilai
    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }
    public void setvelocityY(int velocityY){ this.velocityY = velocityY; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setImage(Image image) { this.image = image; }
}
