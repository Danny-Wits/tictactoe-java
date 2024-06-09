package in.tictactoe;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class audio implements Runnable {
    private static Clip soundEffectClip ;
    private static Clip backgroundClip;
    private static boolean gameOver=false;
    private static boolean clicked=false;
    static{
        try {
            soundEffectClip = AudioSystem.getClip();
            backgroundClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) { 
            e.printStackTrace();
        }
        File bs = new File("game\\src\\main\\java\\in\\tictactoe\\assets\\audio.wav");
        File click = new File("game\\src\\main\\java\\in\\tictactoe\\assets\\impact-109588.wav");
        try {
            AudioInputStream bStream = AudioSystem.getAudioInputStream(bs);
            AudioInputStream cStream = AudioSystem.getAudioInputStream(click);
            soundEffectClip.open(cStream);
            backgroundClip.open(bStream);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
   
    audio(){
        // Audio 
        start();
    }
    private void start() {
       gameOver=false;
       clicked=false;
    }
    @Override
    public void run() {
        while (true) {
            if(gameOver){
                backgroundClip.stop();
                soundEffectClip.stop();
                return;
            }
            if(!backgroundClip.isActive()){
                backgroundClip.start();
                backgroundClip.loop(1);
            }
            if (!soundEffectClip.isActive()&&clicked) {
                soundEffectClip.setMicrosecondPosition(0);
                soundEffectClip.start(); 
                clicked=false;
            }  
        }
    }
    public void stop() {
        System.out.println("stopped");
        gameOver=true;
        clicked=false;
    }
    public void click(){
        clicked=true;
    }
    
}
