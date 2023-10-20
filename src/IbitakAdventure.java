import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.HashMap;
public class IbitakAdventure extends PApplet {
    Ibitak player = new Ibitak(0,0,"/IMAGES/249.png");
    private Ibitak[] ennemies;
    private HashMap<Ibitak, PImage> ibitakImages = new HashMap<>(); 
    int counter = 0;
    PImage backgroundImage;
    public static void main(String[] args) {
        PApplet.main(IbitakAdventure.class);
        
    }

    public void settings() {
        size((int)1000, (int)1000);
        displayDensity((int)2);   

    }
    public void setup() {
        ennemies = new Ibitak[]{ player,
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
            new Ibitak(0, 0, "/IMAGES/022.png"),
        };
        backgroundImage = loadImage("/IMAGES/pokemon.jpg");
        for (var e : ennemies) {  
            e.imag = loadImage(e.name);
        }
        for (var e : ennemies) {  
            ibitakImages.put(e, loadImage(e.name));
        }
        for (var e : ennemies) {
            e.ibitakPosition((int) (Math.random() * (width - e.size)), (int) (Math.random() * (height - e.size)));
        }
        for (var e : ennemies) {
            e.vitesseIbitakx = (int) random(2) > 0 ? 4 : -4;
            e.vitesseIbitaky = (int) random(2) > 0 ? 4 : -4;
        }
        rectMode(CENTER);
        imageMode(CENTER);
    }
    float angle = 0;
    public void draw(){
        frameRate(60);
        image(backgroundImage,500,500,1000,1000);
        //background(200);
        for (var e : ennemies){
            if(e.vie == 0){
                fill(255,0,0);
                ellipse(e.x + e.size/2, e.y + e.size/2,30,10 );
                ellipse(e.x + e.size/2, e.y + e.size/2,10,30 );
            }
        }
        for (var e : ennemies){
            if(e.vie != 0){
                push();
                translate(e.x,e.y);
                rotate(angle);
                image(ibitakImages.get(e) /*e.imag*/,0,0/*e.x,e.y*/,e.size,e.size);
                pop();
            }
        }
        for(var e: ennemies){
            for(var i : ennemies){
                if(e.kollision(i) && (e!=i) && (e.vie != 0 && i.vie != 0) ){
                    if((int)Math.abs(e.x - i.x) <= i.size && ((int)Math.abs(i.x - e.x) >= 0) &&(e.vie != 0 && i.vie != 0)){
                        if((int)Math.abs(e.y - i.y) <= 100 && (e.vie != 0 && i.vie != 0) ){
                            fill(200);
                            push();
                            translate(e.x,e.y);
                            rotate(angle);
                            rect(0,0,e.size,e.size);
                            rect(0,0,e.size,e.size);
                            pop();
                            if(i.y > e.y) i.vie = 0;
                        }
                        else{
                            e.vie = 50; i.vie = 50;
                            e.vitesseIbitakx *= -1;
                            e.vitesseIbitaky *= -1;
                            i.vitesseIbitakx *= -1;
                            i.vitesseIbitaky *= -1;
                        }
                    }
                }
            }
        }
        for (var e : ennemies) {
            if(e.vie !=0){
                e.x += e.vitesseIbitakx;
                if(e.name != player.name) e.y += e.vitesseIbitaky;
            }
        }
        for (var e : ennemies) {
            if (e.x + e.size/2  >= width || e.x - e.size/2 <= 0) {
                if(e.x + e.size/2  >= width) e.vitesseIbitakx = 4;
                if(e.x -e.size/2 <= 0) e.vitesseIbitakx = -4;
                e.vitesseIbitakx  *= -1;
                e.vitesseIbitaky = (int) random(-5, 5) < 0 ?  -4 : 4;
            }
            if (e.y + e.size/2 >= height || e.y -e.size/2    <= 0) {
                if(e.y + e.size/2 >= height) e.vitesseIbitaky = 4;
                if(e.y -e.size/2   <= 0) e.vitesseIbitaky = -4;
                e.vitesseIbitaky  *= -1;
                e.vitesseIbitakx = (int) random(2) > 0 ? 4 : -4;
            }
        }
        textSize(50);
        textAlign(CENTER,CENTER);
        if(player.vie == 0) {
            fill(0,0,0);
            text("LUGIA HAT VERLOREN",500,100);
            textSize(50);
            fill(255,0,0);
            text("click r to replay",510,150);
        }
        if(counter == 10 && player.vie != 0){
            background(0);
            fill(255,255,0);
            image(loadImage("/IMAGES/25.jpg"),500,250,450,450);
            text("LUGIA HAT GEWONNEN",500,500);
            fill(255,0,0);
            text("click r to replay",510,550);
        }
        for(var e : ennemies){
            if(e.vie == 0 && e.image != "died"){
                counter++;
                e.image ="died";
            }
        }
        angle += 0.25; 
    }
    public void keyPressed(KeyEvent event){
        if(event.getKeyCode() == UP && player.y >= 0 ){
            player.y -= 10;
        }
        if(event.getKeyCode() == DOWN && player.y + player.size <= height ){
            player.y += 10;
        }
        if(event.getKeyCode() == 'R'){
            for(var e : ennemies) e.vie = 50;
            counter = 0;
            setup();
           draw();
        }  
    }
}
