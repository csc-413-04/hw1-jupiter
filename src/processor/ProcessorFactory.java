package processor;

import request.Request;

public class ProcessorFactory {
    public static Processor makeProcessor(Request request) {
        String endpoint = request.getEndpoint();
        switch(endpoint) {
            case "/user":
                return new UserProcessor(request);
            case "/posts":
                return new PostProcessor(request);
        }
        return null;
    }
}
