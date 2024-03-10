package blackjack.model.user

import blackjack.model.Player

object UserInputValidator {
    fun checkPlayers(input: List<String>): Result<List<Player>> {
        return runCatching {
            input.map { name -> Player(name) }
        }
    }

    fun checkUserDecision(input: String): Result<UserDecision> {
        return runCatching {
            UserDecision.getUserDecision(input)
        }
    }
}
