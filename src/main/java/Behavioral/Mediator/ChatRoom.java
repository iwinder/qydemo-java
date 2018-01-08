package Behavioral.Mediator;

import Utills.PrintUtill;

import java.util.Date;

public class ChatRoom {
    public static void showMessage(User user,String message){
        PrintUtill.println(new Date().toString()
                + "["+ user.getName() +"] ："+message);
    }
}
