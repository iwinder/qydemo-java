package DesignPatterns.Behavioral.Command.AudioPlayerDemo;

import Utills.PrintUtill;

/**
 * 1、接受者角色，扮演者-录音机/MP3类，音频播放器
 */
public class AudioPlayer {
    public void play(){
        PrintUtill.println("播放------");
    }

    public void cut(){
        PrintUtill.println("切歌---------");
    }

    public void stop(){
        PrintUtill.println("停止------");
    }


}
