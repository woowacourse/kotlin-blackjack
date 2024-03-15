package blackjack.view.user

import blackjack.model.BettingMoney
import blackjack.model.Player

object UserInputValidator {
    fun checkPlayers(input: List<String>): Result<List<Player>> {
        return runCatching {
            Player.checkDuplication(input)
            input.map { name -> Player(name) }
        }
    }

    fun checkUserDecision(input: String): Result<UserDecision> {
        return runCatching {
            UserDecision.getUserDecision(input)
        }
    }

    fun checkBettingMoney(input: Int?): Result<BettingMoney> {
        return runCatching {
            BettingMoney(input)
        }
    }
}
