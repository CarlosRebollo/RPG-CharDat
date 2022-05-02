package ies.quevedo.chardat.network

import ies.quevedo.chardat.domain.model.Personaje
import retrofit2.Response
import retrofit2.http.*

interface PersonajeService {

    @GET("api/personajes/{id}")
    suspend fun getPersonaje(@Path("id") id: Int): Response<Personaje>

    @GET("api/personajes")
    suspend fun getPersonajes(): Response<List<Personaje>>

    @POST("api/personajes")
    suspend fun postPersonaje(@Body personaje: Personaje): Response<Personaje>

    @PUT("api/personajes")
    suspend fun putPersonaje(@Body personaje: Personaje): Response<Personaje>

    @DELETE("api/personajes")
    suspend fun deletePersonaje(@Query("idPersonaje") idPersonaje: Int): Response<Personaje>
}