package com.example.smj.data.entity.Member;

import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.data.entity.Comments.CommentsPostData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Entity_Member {
    @GET("api/member")
    Call<List<MemberData>> getData(
            @Header("Authorization") String token,
            @Path("board_id") int id
    );

    @PUT("api/member/{member_id}")
    Call<MemberData> putData(
            @Header("Authorization") String token,
            @Body MemberPostData body,
            @Path("member_id") int id
    );

    @DELETE("api/member/{member_id}")
    Call<Void> deleteData(
            @Header("Authorization")String token,
            @Path("member_id") int id
    );
}
