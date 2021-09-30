package com.example.bookfetch;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookfetchApi {

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("books/{book}")
    Call<Book> getBook(@Path("book") String book);

    @POST("books")
    @Headers("Content-Type: application/json")
    Call<Book> createBook(@Body Book book);
}