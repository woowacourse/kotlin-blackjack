package blackjack.view.user

import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView

object UserInputValidator {
    fun checkPlayers(input: List<String>): List<String>? {
        return try {
            checkPlayerNameDuplication(input)
            return input
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

    fun makeParticipants(): Participants {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        return checkParticipants(dealer, players) ?: makeParticipants()
    }

    private fun checkParticipants(
        dealer: Dealer,
        players: List<Player>,
    ): Participants? {
        return try {
            Participants(
                dealer = dealer,
                players = players,
            )
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun checkPlayerNameDuplication(playerNames: List<String>) {
        require(playerNames.size == playerNames.toSet().size) {
            Player.ERROR_DUPLICATION_NAME
        }
    }

    const val ERROR_INACCURATE_MONEY = "잘못 된 베팅 금액입니다."
}
