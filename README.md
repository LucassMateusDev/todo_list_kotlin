# Todo List App 📝

Um aplicativo de todo simples e moderno, construído com as tecnologias mais recentes do ecossistema Android.

## ✨ Funcionalidades

* **Adicionar e Editar Tarefas:** Crie novas tarefas ou edite as existentes de forma intuitiva.
* **Listagem das Tarefas:** Visualize todas as suas tarefas em uma lista.
* **Marcar como Concluída:** Marque tarefas como finalizadas com um simples toque.
* **Excluir Tarefas:** Remova tarefas que não são mais necessárias.
* **Persistência Local:** Todas as tarefas são salvas localmente no dispositivo, garantindo que seus dados não sejam perdidos ao fechar o app.

---

## 🛠️ Tecnologias e Arquitetura

Este projeto foi construído com foco em uma arquitetura limpa e escalável.

### Arquitetura
* **MVVM (Model-View-ViewModel):** Padrão de arquitetura que separa a lógica de negócio da interface do usuário, facilitando a manutenção e os testes.
    * **View (Compose UI):** Camada de interface, reativa e declarativa.
    * **ViewModel (Jetpack ViewModel):** Gerencia o estado da UI e se comunica com a camada de dados.
    * **Model (Repository e Room):** Abstrai a origem dos dados (neste caso, o banco de dados local).

### Tecnologias utilizadas
* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - Toolkit moderno para construir interfaces de usuário nativas.
* **Injeção de Dependência:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Para gerenciar as dependências de forma simples e robusta.
* **Banco de Dados:** [Room](https://developer.android.com/training/data-storage/room) - Biblioteca de persistência de dados que oferece uma camada de abstração sobre o SQLite.
* **Assincronismo:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html) - Para gerenciar tarefas em segundo plano e fluxos de dados reativos.
* **Navegação:** [Jetpack Navigation for Compose](https://developer.android.com/jetpack/compose/navigation) - Para navegar entre as telas do aplicativo.

---

## 🚀 Como Compilar

1.  Clone o repositório:
    ```bash
    git clone https://github.com/LucassMateusDev/todo_list_kotlin
    ```
2.  Abra o projeto no [Android Studio](https://developer.android.com/studio).
3.  O Gradle cuidará do resto, sincronizando e baixando todas as dependências necessárias.
4.  Execute o aplicativo em um emulador ou dispositivo físico.
