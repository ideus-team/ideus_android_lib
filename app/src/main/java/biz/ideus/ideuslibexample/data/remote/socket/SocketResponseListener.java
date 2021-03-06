package biz.ideus.ideuslibexample.data.remote.socket;

/**
 * Created by walkmn on 08.02.17.
 */

public abstract class SocketResponseListener<T> {

    public Class<T>  getResponseClass() {
        return responseClass;
    }

    private Class<T> responseClass;

    T response;

    public SocketResponseListener (Class<T> responseClass) {
        this.responseClass = responseClass;
    }

    public SocketResponseListener () {
    }

    protected abstract void onGotResponseData(T data);
}
