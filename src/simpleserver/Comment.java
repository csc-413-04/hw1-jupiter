package simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Comment {
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private static Map<Integer, Comment> commentDict = new HashMap<>();
    private static ArrayList<Comment> allcomments = new ArrayList<>();

    private  String data;
    private  int userid;
    private  int postid;

    public void setPostid(int postid){
        this.postid = postid;
        System.out.println(postid);
    }


    public Comment(){
        allcomments.add(this);
    }

    public Comment(String data, int postid, int userid){
        this.data = data;
        this.postid = postid;
        this.userid = userid;
        allcomments.add(this);
        commentDict.put(postid, this);
    }

    public static Comment getComment(int postid) {
        return  commentDict.get(postid);
    }

    public void register() {
        commentDict.put(postid, this);
    }

    public static void loadAll(){
        for(int i=0; i < allcomments.size(); i++){
            allcomments.get(0).register();
        }
    }
}
