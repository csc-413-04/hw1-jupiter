package processor;


import endpoints.Endpoints;
import request.Request;
import response.Response;

import java.util.Map;

public class Processor {
    private Map<String, String> parameters;
    private Integer userId;
    private Integer postId;
    private Integer maxLength;
    Response response;
    Endpoints endpoint;
    public Processor () {
        
    }
    public Processor(Request request) {
        this.parameters = request.getParameters();
        userId = null;
        postId = null;
        maxLength = null;
        this.response = new Response();
        this.endpoint = new Endpoints();
    }
    public void loadPrameter() {
        if (parameters.containsKey("userid")) {
            this.userId = Integer.valueOf(parameters.get("userid"));
        }
        if (parameters.containsKey("postid")) {
            this.postId = Integer.valueOf(parameters.get("postid"));
        }
        if (parameters.containsKey("maxlength")) {
            this.maxLength = Integer.valueOf(parameters.get("maxlength"));
        }
    }
    public Response process(Request request) throws Exception {
        return response;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public Integer getPostId() {
        return postId;
    }
    
    public Integer getMaxLength() {
        return maxLength;
    }
    
}
