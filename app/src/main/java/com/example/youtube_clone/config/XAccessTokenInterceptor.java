package com.example.youtube_clone.config;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.youtube_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.youtube_clone.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        //final String jwtToken = X_ACCESS_TOKEN;

        //실제로는 이거 지워야함
        sSharedPreferences.edit().putString(X_ACCESS_TOKEN, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJ1c2VySWQiOiJzdW4iLCJpYXQiOjE1OTU3NTM4MDksImV4cCI6MTYyNzI4OTgwOSwic3ViIjoidXNlckluZm8ifQ.yF3ZnM6DkK0VZtJenWM1BqXNwxJIER5A-RzFbFC3Nk4").apply();
        if (jwtToken != null) {
            builder.addHeader("x-access-token", jwtToken);
        }
        return chain.proceed(builder.build());
    }
}
