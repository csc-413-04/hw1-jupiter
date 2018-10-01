package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Post {
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, Post> postidDict = new HashMap<>();
    private static ArrayList<Post> allPosts = new ArrayList<>();//make it static so that every time new posts are pushed to this list
    private int userid;
    private int postid;
    private String data;

    public Post(Post original) {
        data = null;
        postid = -1;
        userid = -1;
    }
    public Post(){
        allPosts.add(this);
    }
    public Post(String postContent, int postid, int userid){
        this.data = postContent;
        this.postid = postid;
        this.userid = userid;
        allPosts.add(this);
        postidDict.put(postid, this);
    }
    public static void loadAll(){
        for(int i = 0 ; i < allPosts.size(); i++){
            allPosts.get(i).register();
        }
    }
    public void register(){
        postidDict.put(postid, this);
    }
    public static Map getInstance() { //singleton
        return postidDict;
    }

    public String getData() {
        return data;
    }

    //    public void setPostContent(int maxLength) {
//        if (maxLength < this.data.length()) {
//            this.data = this.data.substring(0, maxLength);
//        }
//    }
}
