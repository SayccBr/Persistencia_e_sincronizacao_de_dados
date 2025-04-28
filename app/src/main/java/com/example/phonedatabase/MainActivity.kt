package com.example.phonedatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.phonedatabase.database.DatabaseProvider
import com.example.phonedatabase.database.entities.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var messageEditText: EditText
    private lateinit var addButton: Button
    private lateinit var textViewMessages: TextView

    private val database by lazy { DatabaseProvider.getDatabase(this) }
    private val messageDao by lazy { database.messageDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageEditText = findViewById(R.id.messageEditText)
        addButton = findViewById(R.id.addButton)
        textViewMessages = findViewById(R.id.textViewMessages)

        // Clicar no botão para adicionar uma nova mensagem
        addButton.setOnClickListener {
            val messageContent = messageEditText.text.toString()
            if (messageContent.isNotBlank()) {
                saveMessage(messageContent)
            }
        }

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
}
