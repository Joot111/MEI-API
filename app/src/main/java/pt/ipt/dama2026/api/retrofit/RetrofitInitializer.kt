package pt.ipt.dama2026.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import pt.ipt.dama2026.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Classe que irá estabelecer a ligação entre a API e a aplicação.
 */
class RetrofitInitializer {
    // objeto que irá traduzir o conteúdo do JSON recebido para a nossa 'app'
    private val gson: Gson = GsonBuilder().setLenient().create()
    // endereço da API
    private val host = "https://adamastor.ipt.pt/API/"

    // estabelece a ligação entre a API e a aplicação
    private val retrofit =
        Retrofit.Builder().baseUrl(host)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // recebe os dados da API, para eles possam fazer alguma coisa
    fun noteService(): NoteService = retrofit.create(NoteService::class.java)
}