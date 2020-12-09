package ipvc.estg.room2.api

import retrofit2.Call
import retrofit2.http.*

// Colocar ou substituir os Endpoints necessarios


interface EndPoints {

    @GET("/meuslim/api/maps")
    //@GET("/users/")
    fun getUsers(): Call<List<User>>

    @GET("/meuslim/api/maps/{nome_user}")
    fun getUserById(@Path("id") id: Int?): Call<List<User>>

    @GET("/meuslim/api/maps2/{nome_user}")
    fun getIdByUser(@Path("nome_user") nome_user: String?): Call<List<iduser>>

    @FormUrlEncoded
    @POST("/posts")
    fun postTest(@Field("title") first: String?): Call<OutputPost>

    @FormUrlEncoded
    @POST("/meuslim/api/login2")
    fun login(  @Field("nome_user") first: String?,
                @Field("pass") pass: String?): Call<OutputPost>


    @FormUrlEncoded
    @POST("/meuslim/api/add_acidente")
    fun add_acidente(   @Field("descricao") descricao: String,
                        @Field("lat") lat: Double?,
                        @Field("lng") lng: Double?,
                        @Field("id") id: Int?,
                        @Field("tipo") tipo: Int? ): Call<OutputPost>

}