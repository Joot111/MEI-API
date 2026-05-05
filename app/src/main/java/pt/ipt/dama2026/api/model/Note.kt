package pt.ipt.dama2026.api.model

import com.google.gson.annotations.SerializedName

/*
 * Classe que irá receber os objetos vindos da API, neste caso, as notas.
 */
data class Note(
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "description") val description: String)

/* @SerializedName("title") -- lê o atributo vindo do JSON
 * title: String -- nome da variável que irá receber o valor do atributo
 */