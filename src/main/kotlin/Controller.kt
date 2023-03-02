import domain.UserNameContainer
import view.LoginView

class Controller(
    private val loginView: LoginView = LoginView(),
) {

    fun run() {
        repeatWithRunCatching { getPlayerNames() }
    }

    private fun getPlayerNames(): UserNameContainer = UserNameContainer(loginView.requestPlayerName())

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }
}
