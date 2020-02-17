/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import transfer.util.ResponseStatus;

/**
 *
 * @author student1
 */
public class Response implements Serializable {
    private ResponseStatus status;
    private Exception error;
    private Object data;

    public Response(ResponseStatus status, Exception error, Object data) {
        this.status = status;
        this.error = error;
        this.data = data;
    }

    public Response() {
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
