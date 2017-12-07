package Structural.Adapter;

/**
 * Description:
 * User: wind
 * Date: 2017-06-21
 * Time: 18:48 下午
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file.Name : "+fileName);
    }

    public void playMp4(String fileName) {

    }
}
