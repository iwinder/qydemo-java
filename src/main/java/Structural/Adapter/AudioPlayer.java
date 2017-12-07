package Structural.Adapter;

/**
 * Description:创建实现了 MediaPlayer 接口的实体类。
 * User: wind
 * Date: 2017-06-22
 * Time: 12:19 下午
 */
public class AudioPlayer implements MediaPlayer {
    //创建适配器实例
    MediaAdapter mediaAdapter;
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+fileName);
        }else if(audioType.equalsIgnoreCase("vlc")||audioType.equalsIgnoreCase("mp4")){
            //mediaAdpter 提供了播放其他文件格式的支持
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType,fileName);
        }else{
            System.out.println("Invalid media. "+ audioType +" format not supported.");
        }
    }
}
