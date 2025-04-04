package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * @author : sachini Apsara
 * @date : 2025-04-04
 * @Project: Sentura Technologies Interview Task user auth
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final OkHttpClient okHttpClient;
    private final String baseUrl = "https://c35013e82272434488987d00b49bfbf7.weavy.io/api/users";
    private final String apiToken = "wys_pWLw7EZDEOQJOMKPRw6BicbjO0FclH4J7TCq";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    public String createUser(String jsonPayload) throws IOException {
        RequestBody body = RequestBody.create(jsonPayload,JSON);
        Request request = new Request.Builder()
                .url(baseUrl)
                .post(body)
                .addHeader("Authorization","Bearer"+apiToken)
                .build();
        try(Response response = okHttpClient.newCall(request).execute()){
            return response.body().string();
        }
    }

    @Override
    public String listUsers(int take) throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(baseUrl);
        if (httpUrl == null) {
            throw new IllegalArgumentException("Invalid base URL: " + baseUrl);
        }

        HttpUrl builtUrl = httpUrl.newBuilder()
                .addQueryParameter("take", String.valueOf(take))
                .build();

        Request request = new Request.Builder()
                .url(builtUrl)
                .get()
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
            return response.body().string();
        }
    }


    @Override
    public String getUserDetails(String userId) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/" + userId)
                .get()
                .addHeader("Authorization","Bearer" + apiToken)
                .build();
        try(Response response = okHttpClient.newCall(request).execute()){
            return response.body().string();
        }
    }

    @Override
    public String updateUser(String userId, String jsonPayload) throws IOException {
        RequestBody requestBody = RequestBody.create(jsonPayload,JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/" + userId)
                .put(requestBody)
                .addHeader("Authorization","Bearer" + apiToken)
                .build();

        try(Response response = okHttpClient.newCall(request).execute()){
            return response.body().string();
        }
    }

    @Override
    public String deleteUser(String userId) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/" + userId + "/trash")
                .post(RequestBody.create(new byte[0],null))
                .addHeader("Authorization","Bearer" + apiToken)
                .build();
        try(Response response = okHttpClient.newCall(request).execute()){
            return response.body().string();
        }
    }
}
