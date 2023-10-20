import processing.core.PApplet;
import processing.core.PImage;
public class Ibitak extends PApplet{
    public static void main(String[] args) {
        PApplet.main(Ibitak.class);    
    }
    int x, y;
    int size;
    String name;
    int vitesseIbitakx;
    int vitesseIbitaky;
    int vie = 50;
    String image;
    PImage imag ;

    public Ibitak(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.size = 100;
        this.name = name;
    }
    public void ibitakPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean kollision(Ibitak other){
         int d = (int)dist( this.x,this.y,other.x, other.y);
        return d <= 100;
    }
    public void setVie(int vie) {
        this.vie = vie;
    }
}

