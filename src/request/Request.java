package request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Request {
    private String uri;
    private String endpoint;
//    private String verb;
//    private String httpVersion;
    private Map<String, String> parameters;

    public Request(InputStream client) throws Exception{
        parameters = new HashMap<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(client));
        try {
            // read the first line to get the request method, URI and HTTP version
            parseFirstLine(in);
            parseParameter(uri);
        } catch (Exception e) {
            System.out.println("Error create request");
//            throw new HTTPException(400);
        }
    }
    private void parseFirstLine(BufferedReader in) throws Exception{
        String line = in.readLine();
        String[] parameters = line.split(" ");
//        verb = parameters[0];
        uri = parameters[1];
//        httpVersion = parameters[2];
    }
    private void parseParameter(String uri) throws Exception {
        int endpointIndex = uri.indexOf("?");
        if (endpointIndex == -1) {
            this.endpoint = uri;
        } else {
            this.endpoint = uri.substring(0, endpointIndex);
            String parametersStr = uri.substring(endpointIndex + 1, uri.length());
            String[] parametersArr = parametersStr.split("&");
            for (String parameter : parametersArr) {
                String[] pari = parameter.split("=");
                String key = pari[0];
                String value = pari[1];
                parameters.put(key, value);
            }
        }
    }

    //accessors:
    public String getUri() {
        return uri;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
