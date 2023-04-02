package DesignPatterns.Behavioral.Command.AudioPlayerDemo;

/**
 * 　5、客户端角色，由茱丽小女孩扮演
 *      茱丽使用MP3听歌，通过键盘/按键控制播放、切歌、停止
 *      键盘使用命令模式控制MP3执行播放、切歌、停止命令
 */
public class Julia {
    public static void main(String[] args) {
        //创建命令接收者，要用MP3听歌，首先要有一个MP3。
        AudioPlayer audioPlayer = new AudioPlayer();

        //创建命令对象，上面仅是有个壳子，还不存在功能对应的指令，现在需要为MP3添加播放、切歌、停止的功能指令
        Command playCommand = new PlayCommand(audioPlayer);
        Command cutCommand = new CutCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);

        //创建请求对象。MP3有了功能对应的指令，但茱丽是要通过操作键盘/按键来使用这些功能的。按键在内部通过命令模式控制MP3的各种功能实现。
        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCommand);
        keypad.setCutCommand(cutCommand);
        keypad.setStopCommand(stopCommand);

        //测试，茱丽终于可以用拿到的MP3听歌啦
        keypad.play();
        keypad.cut();
        keypad.stop();
        keypad.play();
        keypad.stop();
    }
}
