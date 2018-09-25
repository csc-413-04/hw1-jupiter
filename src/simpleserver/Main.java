package simpleserver;

import com.google.gson.*;
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/data.json"));
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(br).getAsJsonObject();

            User[] users = gson.fromJson(obj.get("users"), User[].class);
            Post[] posts = gson.fromJson(obj.get("posts"), Post[].class);
            Comment[] comments = gson.fromJson(obj.get("comments"), Comment[].class);

            User.loadAll();

            Response response = new Response();
            response.setUsers(users);
            response.setPosts(posts);
            response.setComments(comments);

            String userJsonString = gson.toJson(User.getUser(0));
            String postJsonString = gson.toJson(Post.getPost(0));
            String commentJsonString = gson.toJson(Comment.getComment(0));

            System.out.println(userJsonString);
            System.out.println(postJsonString);
            System.out.println(commentJsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Response{
    User[] users;
    Post[] posts;
    Comment[] comments;

    public void setUsers(User[] users){
        this.users = users;
    }
    public void setPosts(Post[] posts){ this.posts = posts; }
    public void setComments(Comment[] comments){ this.comments = comments; }
}