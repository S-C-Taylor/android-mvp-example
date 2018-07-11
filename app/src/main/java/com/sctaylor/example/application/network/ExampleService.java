package com.sctaylor.example.application.network;


import com.sctaylor.example.models.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by simon on 6/27/2018.
 */

public interface ExampleService {

    @Headers("X-API-Key: 4e46b230")
    @GET("example")
    Single<User> getUser();

}
