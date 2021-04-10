package com.example.smj.data.entity.Comments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Entity_Comments {
    @GET("api/boards/{board_id}/comments")
    Call<List<CommentData>> getComments(
            @Header("Authorization") String token,
            @Path("board_id") int id
    );

    @POST("api/boards/{board_id}/comments")
    Call<CommentData> postComments(
            @Header("Authorization") String token,
            @Body CommentsPostData body,
            @Path("board_id") int id
    );

    @PUT("api/boards/comments/{comments_id}")
    Call<CommentData> putComments(
            @Header("Authorization") String token,
            @Body CommentsPostData body,
            @Path("comments_id") int id
    );

    @DELETE("api/boards/comments/{comments_id}")
    Call<Void> deleteComments(
            @Header("Authorization")String token,
            @Path("comments_id") int id
    );
}
