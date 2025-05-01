package com.example.wear.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import java.io.OutputStreamWriter
import java.net.Socket

class MainActivity : ComponentActivity() {

    private val TAG = "WearSocketClient"
    private val IP = "192.168.1.105"  // Substitua pelo IP correto do seu smartphone físico

    private val PORT = 8080  // Mesma porta usada no servidor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }

    @Composable
    fun WearApp() {
        val message = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF001F3F)) // Fundo da tela azul escuro
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Botão para enviar mensagem
            Button(
                onClick = { sendMessageToPhone(message.value) }, // Envia a mensagem digitada
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red) // Botão vermelho
            ) {
                Text("Enviar")
            }

            // Campo de texto para digitar a mensagem (abaixo do botão)
            BasicTextField(
                value = message.value,
                onValueChange = { message.value = it },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.White)
                    .fillMaxWidth()
            )
        }
    }

    // Envia mensagem para o smartphone
    private fun sendMessageToPhone(message: String) {
        Thread {
            try {
                Log.d(TAG, "Tentando se conectar a $IP:$PORT")  // Log antes da conexão

                val socket = Socket(IP, PORT)

                Log.d(TAG, "Conectado ao servidor")
                val outputStream = socket.getOutputStream()
                val writer = OutputStreamWriter(outputStream)

                writer.write(message)  // Envia a mensagem para o servidor
                writer.flush()

                Log.d(TAG, "Mensagem enviada: $message")

                // Fechando a conexão
                writer.close()
                socket.close()

            } catch (e: Exception) {
                Log.e(TAG, "Erro ao enviar mensagem: ${e.message}")
            }
        }.start()
    }
}
