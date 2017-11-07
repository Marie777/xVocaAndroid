package voca.xvocaandroid;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthorizedJsonRequest extends JsonObjectRequest {
    private Map<String, String> headers = new HashMap<>();

    public AuthorizedJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, String userToken) {
        super(method, url, jsonRequest, listener, errorListener);
        headers.put("Authorization", userToken);
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }
}
