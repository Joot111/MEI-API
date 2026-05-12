package pt.ipt.dama2026.api.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pt.ipt.dama2026.api.R
import pt.ipt.dama2026.api.databinding.ActivityMainBinding
import pt.ipt.dama2026.api.model.Note
import pt.ipt.dama2026.api.retrofit.RetrofitInitializer
import pt.ipt.dama2026.api.ui.adapter.NoteListAdapter
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.util.GregorianCalendar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //listar notas
        listarNotas()

        // adicionar uma nota
        binding.btNewNote.setOnClickListener { addNewNote() }
    }

    /**
     * Adicionar uma nova nota aleatória
     * Avisa a BD da adição de uma nova nota, através da API
     */
    fun addNewNote() {
        // import java.util.GregorianCalendar
        // import kotlin.random.Random
        val i = Random(GregorianCalendar.getInstance().timeInMillis).nextInt(100)
        val note = Note("Nota " + i, "Descrição da Nota $i")

        // invoca a adição de uma nova nota
        addNote(note) {
            Toast.makeText(this, "Adicionada " + it?.description, Toast.LENGTH_LONG).show()
            listarNotas()
        }
    }

    /**
     * function that really add the new note to the API
     */
    private fun addNote(note: Note, onResult: (Note?) -> Unit) {

        val call = RetrofitInitializer().noteService().addNote(note)
        call.enqueue(
            object : Callback<Note> {
                /**
                 * Invoked for a received HTTP response.
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 */
                override fun onResponse(call: Call<Note>, response: Response<Note>) {
                    val addedNote = response.body()
                    onResult(addedNote)
                }

                /**
                 * Invoked when a network exception occurred talking to the server or when an unexpected exception
                 * occurred creating the request or processing the response.
                 */
                override fun onFailure(call: Call<Note>, t: Throwable) {
                    t.printStackTrace()
                    onResult(null)
                }
            }
        )
    }

    /**
     * Listar as notas utilizando o Retrofit
     */
    private fun listarNotas(){
        val call = RetrofitInitializer().noteService().getNotes()
        processList(call)
    }

    /**
     * Processa a resposta da API, para a listagem de notas
     * @param listaNotes variável com a lista de Notas
     */
    private fun processList(listaNotes: Call<List<Note>>) {

        listaNotes.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(
                call: Call<List<Note>?>?,
                response: Response<List<Note>?>?
            ) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    // entrega a lista de Notas, para a sua
                    // efetiva representação no ecrã do telemóvel
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<List<Note>?>?, t: Throwable?) {
                t?.printStackTrace()
                t?.message?.let { Log.e("onFailure error", it) }
            }
        })
    }

    /**
     * Configura a apresentação da listagem das notats na interface
     */
    private fun configureList(notes: List<Note>) {

        binding.nodeList.adapter = NoteListAdapter(notes)

        val layoutManager = StaggeredGridLayoutManager(
            3, StaggeredGridLayoutManager.VERTICAL
        )

        binding.nodeList.layoutManager = layoutManager
    }
}