package pt.ipt.dama2026.api.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ipt.dama2026.api.databinding.NoteItemBinding
import pt.ipt.dama2026.api.model.Note

/**
 * 'ferramenta' para traduzir os dados que recebemos da API para a interface da aplicação,
 * ou seja, para os objetos que vão representar as notas na interface.
 */
class NoteListAdapter(
    private val notes: List<Note>,
) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * processa cada uma das Notas Lidas da API
     * Entrega esses dados à função que, efetivatamente,
     * os irá colocar na interface
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    /**
     * atribui os valores da 'note' ao objeto que os vai representar na interface
     */
    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(note: Note) {
            binding.noteItemTitle.text = note.title
            binding.noteItemDescription.text = note.description
        }
    }
}