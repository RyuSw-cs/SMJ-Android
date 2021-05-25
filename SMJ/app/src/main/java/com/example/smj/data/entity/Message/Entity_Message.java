package com.example.smj.data.entity.Message;

import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.data.entity.Member.MemberPostData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Entity_Message {
    @GET("api/messages")
    Call<List<MessageData>> getData(
            @Header("Authorization") String token
    );

    @POST("api/messages")
    Call<MessageData> postData(
            @Header("Authorization") String token,
            @Body MessagePostData body
    );

    @DELETE("api/messages/{messages_id}")
    Call<Void> deleteData(
            @Header("Authorization")String token,
            @Path("messages_id") int id
    );
}
