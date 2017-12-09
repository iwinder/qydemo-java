package Behavioral.Command.AudioPlayerDemo;

/**
 * 3、具体命令角色类，倒带命令
 */
public class CutCommand implements Command {

    private AudioPlayer audioPlayer;

    public CutCommand(AudioPlayer audioPlayer){
        this.audioPlayer = audioPlayer;
    }

    public void execute() {
        audioPlayer.cut();
    }
}
