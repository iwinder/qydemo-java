package Structural.Adapter;

/**
 * Description:创建实现了 AdvancedMediaPlayer 接口的实体类。
 * User: wind
 * Date: 2017-06-21
 * Time: 18:49 下午
 */
public class Mp4Player implements AdvancedMediaPlayer {
    public void playVlc(String fileName) {

    }

    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name : "+fileName);
    }
}
