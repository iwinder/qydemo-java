package cn.wind.WebScoketDom.utills;

import cn.wind.WebScoketDom.entity.User;

import java.util.*;

/**
 * Created by wind on 2016/11/21.
 */
public class UsrMapUtil {

    private static Map<String, User> users;
    static {
        users = new HashMap<String, User>();
    }
    //添加用户
   public static boolean addUser(User user){
       if(users.get(user.getId())==null){
           users.put(user.getId(),user);
           return true;
       }
       return false;
   }
    //获取用户
    public static User getUser(String uid){
        User user = users.get(uid);
        return user;
    }
    //删除用户
    public static boolean removeUser(String uid){
        if(users.get(uid)!=null){
            users.remove(uid);
            return true;
        }
        return false;
    }
    //获取用户List
    public static List<User> getUserList(){
        List<User> userList = new ArrayList<User>();
        Iterator<Map.Entry<String,User>> it = users
                .entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,User> entry = it.next();
            userList.add(entry.getValue());
        }
        return userList;
    }

    public static boolean findUserYON(String name) {
        Iterator<Map.Entry<String,User>> it = users
                .entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,User> entry = it.next();
            if(entry.getValue().getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
