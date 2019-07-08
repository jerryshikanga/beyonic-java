package com.beyonic.models;

import com.beyonic.Beyonic;
import com.beyonic.exceptions.BeyonicException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jerryshikanga on  2019-07-07
 */
public class BaseModel {
    private static String API_BASE_URL = "https://app.beyonic.us/api/";
    private String API_ENDPOINT;

    public BaseModel(String apiEndpoint) throws BeyonicException {
        // Check if API_KEY has been not set, throw error
        if(Beyonic.API_KEY == null){
            throw new BeyonicException("API Key must be provided");
        }

        // If the user has specified a different Base URL then set to it
        if(Beyonic.API_BASE_URL != null){
            API_BASE_URL = Beyonic.API_BASE_URL;
        }

        this.API_ENDPOINT = apiEndpoint;
    }

    public String list(HashMap<String, String> parameters, HashMap<String, String> headers) throws BeyonicException {
        String requestURL = API_BASE_URL + API_ENDPOINT;
        return makeRequest(requestURL, "GET", null, parameters, headers);
    }

    public String create(@NotNull HashMap<String, Object> payload, HashMap<String, String> headers) throws BeyonicException {
        String requestURL = API_BASE_URL + API_ENDPOINT;
        return makeRequest(requestURL, "POST", payload, null, headers);
    }

    public String create(@NotNull HashMap<String, Object> payload, HashMap<String, String> headers, String duplicateCheckKey) throws BeyonicException {
        String requestURL = API_BASE_URL + API_ENDPOINT;
        headers.put("Duplicate-Check-Key", duplicateCheckKey);
        return makeRequest(requestURL, "POST", payload, null, headers);
    }

    public String get(Integer id) throws BeyonicException {
        String requestURL = API_BASE_URL + API_ENDPOINT +"/"+id;
        return makeRequest(requestURL, "GET", null, null, null);
    }

    public String filter(HashMap<String, String> parameters, HashMap<String, String> headers) throws BeyonicException {
        String requestURL = API_BASE_URL + API_ENDPOINT;
        return makeRequest(requestURL, "GET", null, parameters, headers);
    }

    private String getRequestBody(HashMap<String, Object> hashMap){
        JSONObject jsonObject = new JSONObject();
        if(hashMap != null){
            for (Map.Entry<String, Object> entry : hashMap.entrySet()){
                jsonObject.put(entry.getKey(), entry.getValue());
            }

        }
        return jsonObject.toString();
    }

    private String makeRequest(@NotNull String requestURL, @NotNull String requestMethod, HashMap<String, Object> payload, HashMap<String, String> parameters, HashMap<String, String> headers) throws BeyonicException {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = okhttpBuilder.build();

        // Add headers if supplied
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.addHeader("Authorization", "Token "+Beyonic.API_KEY);
        if (headers!=null){
            for (Map.Entry<String, String> entry : headers.entrySet()){
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // Add Post or patch params
        if(payload != null){
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), this.getRequestBody(payload));
            if(requestMethod.toUpperCase().equals("POST")){
                requestBuilder.post(body);
            }
            if (requestMethod.toUpperCase().equals("PATCH")){
                requestBuilder.patch(body);
            }
        }

        // Add get params
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(requestURL)).newBuilder();
        if(parameters!=null){
            for (Map.Entry<String, String> entry : parameters.entrySet()){
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        // Finally send request
        Request request = requestBuilder.url(urlBuilder.build()).build();
        try{
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        }
        catch (IOException e){
            throw new BeyonicException(e.getMessage());
        }
    }
}
