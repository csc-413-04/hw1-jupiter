package simpleserver;

import com.google.gson.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


class SimpleServer {

  public static void main(String[] args) throws IOException {
    ServerSocket ding;
    Socket dong = null;
    String resource = null;
//--------------load database-----------------------

    loadDatabase();
//--------------------------------------------------
    try {
      ding = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {

        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        try {

          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          System.out.println("----------REQUEST START---------");
          System.out.println(line);
          // read only headers
          line = in.readLine();
          while (line != null && line.trim().length() > 0) {
            int index = line.indexOf(": ");
            if (index > 0) {
              System.out.println(line);
            } else {
              break;
            }
            line = in.readLine();
          }
          System.out.println("----------REQUEST END---------\n\n");
        } catch (IOException e) {
          System.out.println("Error reading");
          System.exit(1);
        }

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // every response will always have the status-line, date, and server name
        writer.println("HTTP/1.1 200 OK");
        writer.println("Server: TEST");
        writer.println("Connection: close");
        writer.println("Content-type: text/html");
        writer.println("");

        // Body of our response
        writer.println("<h1>Some cool response!</h1>");

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }
//--------------------------------------------------------- zihao 9/28
  public static void loadDatabase(){
    Gson gson = new Gson();
    BufferedReader br;

    try {
      br = new BufferedReader(new FileReader("src/data.json"));
      JsonParser jsonParser = new JsonParser();
      JsonObject obj = jsonParser.parse(br).getAsJsonObject();

      User[] users = gson.fromJson(obj.get("users"), User[].class);
      Post[] posts = gson.fromJson(obj.get("posts"), Post[].class);

      User.loadAll();
      Post.loadAll();

      Response response = new Response();
      response.setUsers(users);
      response.setPosts(posts);

      String userJsonString = gson.toJson(User.getUser(0));
      String postJsonString = gson.toJson(Post.getPost(0));

      System.out.println(userJsonString);
      System.out.println(postJsonString);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
//---------------------------------------------------------- zihao 9/28
}
