package DesignPatterns.Behavioral.Command.AudioPlayerDemo;

/**
 * 3、具体命令角色类，停止命令
 */
public class StopCommand implements Command {

    private AudioPlayer audioPlayer;

    public StopCommand(AudioPlayer audioPlayer){
        this.audioPlayer = audioPlayer;
    }

    public void execute() {
        audioPlayer.stop();
    }
}
