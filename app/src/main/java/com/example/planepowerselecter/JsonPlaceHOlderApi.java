package com.example.planepowerselecter;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface JsonPlaceHOlderApi {

    @GET("Item")
    Call<List<Post>> getPosts();

}