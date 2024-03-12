package blackjack.view.user

import blackjack.model.Player

object UserInputValidator {
    fun checkPlayers(input: List<String>): List<Player>? {
        return try {
            checkPlayerNameDuplication(input)
            input.map { name -> Player(name) }
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    fun checkUserDecision(input: String): UserDecision? {
        return try {
            UserDecision.getUserDecision(input)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun checkPlayerNameDuplication(
        playerNames: List<String>
    ) {
        require(playerNames.size == playerNames.toSet().size) {
            Player.ERROR_DUPLICATION_NAME
        }
    }
}
