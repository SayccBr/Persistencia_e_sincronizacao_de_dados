# 🗂️ **Persistência e Sincronização de Dados - Android App** 📱⌚️

**Conexão WearOS e Smartphone para Enviar, Armazenar e Exibir Mensagens usando ROOM e SOCKET** 🔄💬

## 📝 Descrição

Este projeto tem como objetivo implementar a comunicação entre um dispositivo WearOS e um smartphone Android. O smartphone funciona como um servidor, recebendo mensagens enviadas pelo smartwatch, armazenando essas mensagens em um banco de dados local utilizando a biblioteca **Room**, e exibindo-as em uma interface gráfica. A comunicação entre os dispositivos é feita por meio de **sockets**.

O aplicativo do smartphone possui uma funcionalidade de servidor socket, capaz de receber e processar mensagens de um dispositivo WearOS (smartwatch), armazená-las no banco de dados e atualizar a interface com as novas mensagens. Já no smartwatch, a interface permite a inserção de mensagens, que são enviadas diretamente ao smartphone.

## 🚀 Funcionalidades

- **Recepção de Mensagens via Socket**: O smartphone escuta uma porta específica e aceita conexões de dispositivos WearOS, recebendo mensagens enviadas. 📲🔌
- **Armazenamento Local com ROOM**: As mensagens recebidas do smartwatch são armazenadas em um banco de dados local utilizando **Room**, proporcionando persistência e fácil acesso. 💾🛠️
- **Exibição de Mensagens**: As mensagens armazenadas são exibidas na interface gráfica do smartphone, permitindo que o usuário visualize as interações com o smartwatch. 💬📱
- **Envio de Mensagens via WearOS**: O smartwatch oferece uma interface simples para que o usuário envie mensagens ao smartphone, utilizando um botão para facilitar o envio. ⌚️💌

## 🛠️ Tecnologias Utilizadas

- **Android Studio Meerkat**: A versão do Android Studio utilizada para o desenvolvimento deste projeto. 🎨💻
- **Room Database**: Biblioteca para persistência de dados no Android, usada para armazenar as mensagens recebidas localmente no dispositivo. 💾🔒
- **Sockets**: Utilizado para comunicação entre o smartwatch (WearOS) e o smartphone. 🌐🔗
- **Kotlin**: Linguagem principal utilizada no desenvolvimento, tanto para o aplicativo WearOS quanto para o aplicativo Android. ⚡️📲
- **Jetpack Compose**: Usado no projeto WearOS para a criação da interface gráfica. 🎨🖥️
- **AndroidX**: Conjunto de bibliotecas que facilitam o desenvolvimento de aplicativos Android modernos. 📦📱

## 🏃‍♂️ Como Rodar o Projeto

1. O usuário digita a mensagem e pressiona o botão no smartwatch. 🖋️⬆️
2. O smartwatch cria a mensagem e envia ao smartphone via **Sockets**. 📩💨
3. O smartphone captura a mensagem. 📲👀
4. A mensagem é armazenada no banco de dados local usando **Room**. 💾✅
5. A interface do smartphone é atualizada com a nova mensagem recebida. 🔄📱

### 📸 **Smartwatch - Interface para Enviar Mensagens**
   ![WearOS](https://github.com/user-attachments/assets/7d2883d5-0922-49f7-8560-c2001cfdfdf9)

### 📱 **Smartphone - Exibição das Mensagens Recebidas**
   <img src="https://github.com/user-attachments/assets/410292f3-dae3-4953-b545-1804c31a8b5f" height="800" width="430"/>

## ⚙️ Requisitos

- **Android Studio Meerkat** (ou superior)
- **Emuladores de dispositivos Android e WearOS**
- **Conexão Wi-Fi local**: O smartphone e o smartwatch precisam estar na mesma rede local para a comunicação via sockets funcionar corretamente. 🌐🔧

## 📝 Passos para Execução

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/SayccBr/Persistencia_e_sincronizacao_de_dados.git
