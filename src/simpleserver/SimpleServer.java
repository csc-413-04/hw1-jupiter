package simpleserver;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import database.Post;
import database.User;
import processor.Processor;
import processor.ProcessorFactory;
import request.Request;
import response.Response;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static final int DEFAULT_PORT = 1299;
    private static ServerSocket socket;
    private static Socket client = null;

    public static void main(String[] args) throws IOException {
        start();
        loadDatabase();
        while (true) {
            try {
                client = socket.accept(); // waits for client here
                InputStream stream = client.getInputStream();
                Request request = new Request(stream);
                //can check URI is valid (URL, ajex?)
                System.out.println(request.getUri());
                System.out.println(request.getParameters());

                Processor processor = ProcessorFactory.makeProcessor(request);
                Response response = processor.process(request);

                OutputStream outputStream = client.getOutputStream();
                BufferedOutputStream out = new BufferedOutputStream(outputStream);
                PrintWriter writer = new PrintWriter(out, true);  // char output to the client
                writer.println("HTTP/1.1 200 OK");
                writer.println("Server: TEST");
                writer.println("Connection: close");
                writer.println("Content-type: application/json");
                writer.println("");
                // Body of our response
                if (response.getStatus().equals("ERROR")) {
                    writer.println("{“status” : “ERROR” }");
                } else {
                    com.google.gson.Gson gson = new GsonBuilder().setPrettyPrinting().create();;
                    JsonObject object = new JsonObject();
                    JsonElement data = response.getEntries() == 0? gson.toJsonTree(new Object[0]) : gson.toJsonTree(response.getResponseBody());
                    object.add("data", data);
                    object.addProperty("status", "OK");
                    object.addProperty("entries", response.getEntries());
                    String jsonString = gson.toJson(object);
                    writer.println(jsonString);
                }
                client.close();

            } catch (Exception e) {
                sendErrorResponse();
                client.close();
            }
        }
    }

    private static void sendErrorResponse() {
        try {
            OutputStream outputStream = client.getOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
            PrintWriter writer = new PrintWriter(out, true);  // char output to the client
            writer.println("HTTP/1.1 400 Error");
            writer.println("Server: TEST");
            writer.println("Connection: close");
            writer.println("Content-type: application/json");
            writer.println("");
            writer.println("{“status” : “ERROR” }");
            System.out.println("{“status” : “ERROR”}");
        } catch (Exception e) {
            System.out.println("Error opening buffer writer");
            System.exit(1);
        }
    }

    public static void start() throws IOException {
//        ServerSocket socket;
        try {
            socket = new ServerSocket( DEFAULT_PORT );
            System.out.println("Opened socket " + DEFAULT_PORT);
            client = null;
        } catch (IOException e) {
            System.out.println("Error opening socket");
            System.exit(1);
        }
    }

    private static void loadDatabase () {
        com.google.gson.Gson gson = new com.google.gson.Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/data.json"));
            com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
            com.google.gson.JsonObject obj = jsonParser.parse(br).getAsJsonObject();
            User[] users = gson.fromJson(obj.get("users"), User[].class);
            User.loadAll();
            Post[] posts = gson.fromJson(obj.get("posts"), Post[].class);
            Post.loadAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
