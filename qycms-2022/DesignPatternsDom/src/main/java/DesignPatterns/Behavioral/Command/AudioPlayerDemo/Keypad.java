package DesignPatterns.Behavioral.Command.AudioPlayerDemo;

/**
 * 4、请求者角色，扮演者-键盘类
 */
public class Keypad {
    private Command playCommand;
    private Command cutCommand;
    private Command stopCommand;

    public void setPlayCommand(Command playCommand) {
        this.playCommand = playCommand;
    }
    public void setCutCommand(Command cutCommand) {
        this.cutCommand = cutCommand;
    }
    public void setStopCommand(Command stopCommand) {
        this.stopCommand = stopCommand;
    }

    /**
     * 执行播放方法
     */
    public void play(){
        playCommand.execute();
    }
    /**
     * 执行切歌方法
     */
    public void cut(){
        cutCommand.execute();
    }
    /**
     * 执行停止方法
     */
    public void stop(){
        stopCommand.execute();
    }
}
