package processor;

import database.User;
import request.Request;
import response.Response;

public class UserProcessor extends Processor {

    public UserProcessor(Request request) {
        super(request);
        this.loadPrameter();
    }

    @Override
    public Response process(Request request) throws Exception{
        if (this.getUserId() == null) {
            User[] result = this.endpoint.getUsers();
            this.response.setStatus("OK");
            this.response.setEntries(endpoint.totalUser());
            this.response.setUsers(result);
        } else {
            User result = endpoint.getUsers(this.getUserId());
            if (result == null) {
                this.response.setStatus("ERROR");
            } else {
                this.response.setStatus("OK");
                this.response.setEntries(1);
                this.response.setUser(result);
            }
        }
        return this.response;
    }
}
