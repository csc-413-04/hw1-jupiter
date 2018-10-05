package endpoints;

import database.Post;
import database.User;
import java.util.*;

public class Endpoints {

    public int totalUser() {
        Map map = User.getInstance();
        return map.size();
    }
    public User[] getUsers() {
        System.out.println("'getAllUsers' API called");
        //TODO: implement this endpoint which returns all users
        Map map = User.getInstance();
        User[] result = new User[map.size()];
        int i = 0;
        for (Object id : map.keySet()) {
            result[i] = (User)map.get(id);
            i++;
        }
        return result;
    }

    public User getUsers(int userID) {
        System.out.println("'getUserById' API called");
        //TODO: implement this endpoint which returns the user object specified by usedId
        Map map = User.getInstance();
        User user= (User) map.get(userID);
        return user;
    }


    public Post getPost(int postID) {
        System.out.println("'getPostById' API called");
        //TODO: implement this endpoint which returns all posts by ID
        Map map = Post.getInstance();
        Post post= (Post) map.get(postID);
        return post;
    }


    public Post getPost(int postID, int maxLength) {
        System.out.println("'getPostByIdLength' API called");
        //TODO: implement this endpoint which returns all posts by ID
        Map map = Post.getInstance();
        Post post = (Post) map.get(postID);
        if (post != null) {
            if (maxLength < post.getData().length()) {
                return new Post();
            } else {
                return post;
            }
        }
        return null;
    }

}
