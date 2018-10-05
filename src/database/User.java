package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, User> useridDict = new HashMap<>();
    private static ArrayList<User> allUsers = new ArrayList<>();//make it static so that every time new users are pushed to this list
    private int userid;
    private String username;


    public User(){
        allUsers.add(this);
    }
    public User(String username, int userid){
        this.userid = userid;
        this.username = username;
        allUsers.add(this);
        useridDict.put(userid, this);
    }
    public static void loadAll(){
        for(int i = 0 ; i < allUsers.size(); i++){
            allUsers.get(i).register();
        }
    }
    public void register(){
        useridDict.put(userid, this);
    }
    public static Map getInstance() { //singleton
        return useridDict;
    }
}
