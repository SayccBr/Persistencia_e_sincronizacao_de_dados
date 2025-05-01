package com.example.phonedatabase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.phonedatabase.database.DatabaseProvider
import com.example.phonedatabase.database.entities.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.ServerSocket

class MainActivity : AppCompatActivity() {

    private var serverSocket: ServerSocket? = null
    @Volatile private var isServerRunning = true  // Flag para manter o servidor em execução


    private val TAG = "PhoneSocketServer"
    private val PORT = 8080  // Porta que o servidor irá escutar

    private lateinit var messageEditText: EditText
    private lateinit var addButton: Button
    private lateinit var textViewMessages: TextView
    private lateinit var clearButton: Button


    private val database by lazy { DatabaseProvider.getDatabase(this) }
    private val messageDao by lazy { database.messageDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageEditText = findViewById(R.id.messageEditText)
        addButton = findViewById(R.id.addButton)
        textViewMessages = findViewById(R.id.textViewMessages)
        clearButton = findViewById(R.id.clearButton)

        // Clicar no botão para adicionar uma nova mensagem
        addButton.setOnClickListener {
            val messageContent = messageEditText.text.toString()
            if (messageContent.isNotBlank()) {
                saveMessage(messageContent)
            }
        }

        // Clicar no botão para remover todas as mensagens
        clearButton.setOnClickListener {
            deleteAllMessages()
        }

        startSocketServer()
        // Para já mostrar mensagens existentes ao abrir o app
        loadMessages()
    }

    private fun saveMessage(content: String) {
        CoroutineScope(Dispatchers.IO).launch {
            messageDao.insert(Message(content = content))
            loadMessages()
        }
    }


    private fun loadMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            val messages = messageDao.getAllMessages()
            runOnUiThread {
                textViewMessages.text = messages.joinToString("\n") { it.content }
            }
        }
    }

    private fun deleteAllMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            messageDao.deleteAll()
            loadMessages() // Atualiza a UI
        }
    }


    // Inicia o servidor de socket no smartphone, recebe a mensagem e inclui no banco de dados
    private fun startSocketServer() {
        Thread {
            try {
                serverSocket = ServerSocket(PORT)

                //val serverSocket = ServerSocket(PORT, 0, InetAddress.getByName("10.0.2.15"))
                //serverSocket.bind(InetSocketAddress("0", PORT))//teste dois emuladores

                Log.d(TAG, "Servidor iniciado e aguardando conexões...")

                while (isServerRunning) {  // Loop infinito para continuar aceitando conexões
                    val socket = serverSocket?.accept() ?: break  // Aguarda a conexão do WearOS
                    Log.d(TAG, "Conexão recebida de: ${socket.inetAddress}")

                    val inputStream = socket.getInputStream()
                    val reader = InputStreamReader(inputStream)
                    val message = reader.readText()  // Lê a mensagem enviada pelo WearOS

                    Log.d(TAG, "Mensagem recebida: $message")

                    // Atualizar a UI para mostrar a mensagem recebida
                    runOnUiThread {
                        Log.d(TAG, "Atualizando a interface com a mensagem recebida")
                        // Atualize a interface do usuário, como exibir a mensagem recebida em um TextView.
                        // Suponha que exista um TextView com o ID 'messageTextView':
                        // val textView = findViewById<TextView>(R.id.messageTextView)
                        // textView.text = message

                        //adiciona no banco de dados
                        saveMessage(message)
                        loadMessages()
                    }

                    // Fechando conexões
                    reader.close()
                    socket.close()
                    Log.d(TAG, "Conexão tratada e fechada.")
                }

            } catch (e: Exception) {
                Log.e(TAG, "Erro no servidor: ${e.message}")
            } finally {
                try {
                    serverSocket?.close()
                    Log.d(TAG, "Servidor fechado manualmente.")
                } catch (e: Exception) {
                    Log.e(TAG, "Erro ao fechar servidor: ${e.message}")
                }
            }
        }.start()
    }

    //Função para desligar manualmente (adicionar a um botão se quiser)
    private fun stopSocketServer() {
        isServerRunning = false
        try {
            serverSocket?.close()
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao fechar servidor: ${e.message}")
        }
    }

}
