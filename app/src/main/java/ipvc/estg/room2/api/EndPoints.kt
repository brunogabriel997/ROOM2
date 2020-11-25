package ipvc.estg.room2.api

import retrofit2.Call
import retrofit2.http.*

// Colocar ou substituir os Endpoints necessarios


interface EndPoints {

    @GET("/api/escolasdetalhe/")
    //@GET("/users/")
    fun getUsers(): Call<List<User>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @FormUrlEncoded
    @POST("/posts")
    fun postTest(@Field("title") first: String?): Call<OutputPost>
}