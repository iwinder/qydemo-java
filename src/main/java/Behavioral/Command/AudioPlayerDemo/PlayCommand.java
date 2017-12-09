package Behavioral.Command.AudioPlayerDemo;

/**
 * 3、具体命令角色类，播放命令
 */
public class PlayCommand implements Command {

    private AudioPlayer audioPlayer;

    public PlayCommand(AudioPlayer audioPlayer){
        this.audioPlayer = audioPlayer;
    }

    public void execute() {
        audioPlayer.play();
    }
}
