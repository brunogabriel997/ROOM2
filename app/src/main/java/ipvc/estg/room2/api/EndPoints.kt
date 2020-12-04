package ipvc.estg.room2.api

import retrofit2.Call
import retrofit2.http.*

// Colocar ou substituir os Endpoints necessarios


interface EndPoints {

    @GET("/meuslim/api/maps")
    //@GET("/users/")
    fun getUsers(): Call<List<User>>

    @GET("/meuslim/api/maps/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @FormUrlEncoded
    @POST("/posts")
    fun postTest(@Field("title") first: String?): Call<OutputPost>

    @FormUrlEncoded
    @POST("/meuslim/api/escola/login2")
    fun login(  @Field("nome_user") first: String?,
                @Field("pass") pass: String?): Call<OutputPost>
}