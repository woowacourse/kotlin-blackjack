package blackjack.view.user

import blackjack.model.BettingMoney
import blackjack.model.Player

const val ERROR_DECISION = "잘못 된 결정입니다."
const val PLAYER_HIT_MESSAGE = "y"
const val PLAYER_STAY_MESSAGE = "n"

object UserInputValidator {
    fun checkPlayers(input: List<String>): Result<List<Player>> {
        return runCatching {
            Player.checkDuplication(input)
            input.map { name -> Player(name) }
        }
    }

    fun checkUserDecision(input: String): Result<Unit> {
        return runCatching {
            if (input != PLAYER_HIT_MESSAGE && input != PLAYER_STAY_MESSAGE) {
                throw IllegalArgumentException(ERROR_DECISION)
            }
        }
    }

    fun checkBettingMoney(input: Double): Result<BettingMoney> {
        return runCatching {
            BettingMoney(input)
        }
    }
}
