package response;

import database.Post;
import database.User;

public class Response {
    private String status;
    private int entries;
    private Object[] data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEntries(int entries) {
        this.entries = entries;
    }

    public void setUsers(User[] users){
        this.data = users;
    }

    public void setUser(User user){
        data = new Object[1];
        data[0] = user;
    }

    public void setPost(Post post){
        data = new Object[1];
        data[0] = post;
    }

    public String getStatus() {
        return status;
    }

    public int getEntries() {
        return entries;
    }

    public Object[] getResponseBody() {
        return data;
    }


}
