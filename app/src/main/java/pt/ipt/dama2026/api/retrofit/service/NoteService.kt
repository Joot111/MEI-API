package pt.ipt.dama2026.api.retrofit.service

import pt.ipt.dama2026.api.model.Note
import retrofit2.Call
import retrofit2.http.GET

/**
 * descreve as ações que podem ser executadas com a API
 */
interface NoteService {

    /**
     * lê os dados vindos da API e colaca-os numa
     * Lista de Notas
     */
    @GET("api/notes")
    fun getNotes(): Call<List<Note>>
}