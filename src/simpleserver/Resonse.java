package simpleserver;

class Response{
    User[] users;
    Post[] posts;

    public void setUsers(User[] users){
        this.users = users;
    }
    public void setPosts(Post[] posts){ this.posts = posts; }

}