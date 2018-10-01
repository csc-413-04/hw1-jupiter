package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Post {
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private static Map<Integer, Post> postidDict = new HashMap<>();
    private static ArrayList<Post> allPosts = new ArrayList<>();

    private  String data;
    private  int userid;
    private  int postid;

    public void setPostid(int postid){
        this.postid = postid;
        System.out.println(postid);
    }
    public String getData(){
        return data;
    }

    public Post(){
        allPosts.add(this);
    }

    public Post(String data, int postid, int userid){
        this.data = data;
        this.postid = postid;
        this.userid = userid;
        allPosts.add(this);
        postidDict.put(postid, this);
    }

    public static Post getPost(int postid) {
        return  postidDict.get(postid);
    }

    public void register() {
        postidDict.put(postid, this);
    }

    public static void loadAll(){
        for(int i=0; i < allPosts.size(); i++){
            allPosts.get(0).register();
        }
    }

    public static Map getInstance(){
        return postidDict;
    }
}