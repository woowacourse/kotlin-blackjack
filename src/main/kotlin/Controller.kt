import view.LoginView

class Controller(
    private val loginView: LoginView = LoginView(),
) {

    fun run() {
        getPlayerNames()
    }

    private fun getPlayerNames(): List<String> {
        val playerNames = loginView.requestPlayerName()
        if (playerNames.contains("")) getPlayerNames()

        return playerNames
    }
}
