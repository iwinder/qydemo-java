package DesignPatterns.Structural.Adapter;

/**
 * Description: 为媒体播放器和更高级的媒体播放器创建接口。
 * User: wind
 * Date: 2017-06-21
 * Time: 18:46 下午
 */
public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}
