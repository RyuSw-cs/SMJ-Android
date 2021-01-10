package com.example.smj.ui.main.fragment.Convenience.entity;

import com.example.smj.ui.main.fragment.Convenience.remote.Category;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/*
레트로핏 통신을 위한 객체를 만들어 주는 곳
 */
public interface Entity_Convenience {
    //장소이름으로 검색
    @GET("v2/local/search/keyword.json")
    Call<Category>getSearchLocation(
            @Header("Authorization")String token,
            @Query("query")String query,
            @Query("size")int size
    );
    //장소이름으로 검색
    @GET("v2/local/search/keyword.json")
    Call<Category>getSearchCategory(
            @Header("Authorization")String token,
            @Query("query")String query,
            @Query("size")int size,
            @Query("x")String x,
            @Query("y")String y,
            @Query("radius")int radius
    );
    //장소이름으로 검색
    @GET("v2/local/search/keyword.json")
    Call<Category>getSearchLocationDetail(
            @Header("Authorization")String token,
            @Query("query")String query,
            @Query("x")String x,
            @Query("y")String y,
            @Query("radius")int radius
    );
    //장소이름으로 검색
    @GET("v2/local/search/keyword.json")
    Call<Category>getSearchAddress(
            @Header("Authorization")String token,
            @Query("query")String query,
            @Query("size")int size
    );
}
