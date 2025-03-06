package blackjack.view

class InputView {
    private val outputView = OutputView()

    fun readPlayerNames(): List<String> {
        outputView.requestPlayerNames()
        val input: String = readln()
        return input.split(PLAYER_NAMES_DELIMITER).map { name: String -> name.trim() }
    }

    fun readPlayerAction(): String {
        outputView.requestPlayerAction()
        val input: String = readln()
        return input
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
    }
}
