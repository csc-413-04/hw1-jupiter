package processor;

import database.Post;
import request.Request;
import response.Response;

public class PostProcessor extends Processor {

    public PostProcessor(Request request) {
        super(request);
        this.loadPrameter();
    }

    @Override
    public Response process(Request request) throws Exception {
        if (this.getMaxLength() == null) {
            Post result = this.endpoint.getPost(this.getPostId());
            if (result == null) {
                this.response.setStatus("ERROR");
            } else {
                this.response.setStatus("OK");
                this.response.setEntries(1);
                response.setPost(result);
            }
        } else {
            Post result = this.endpoint.getPost(this.getPostId(), this.getMaxLength());
            if (result == null) {
                this.response.setStatus("ERROR");
            } else {
                this.response.setStatus("OK");
                if (result.getData() == null) {
                    this.response.setEntries(0);
                } else {
                    this.response.setEntries(1);
                }
                response.setPost(result);
            }
        }
        return response;
    }
}
