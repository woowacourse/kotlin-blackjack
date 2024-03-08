package blackjack.model.user

import blackjack.model.Player

object UserInputValidator {
    fun checkPlayers(input: List<String>): List<Player>? {
        return try {
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
}
