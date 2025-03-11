package blackjack.view

import blackjack.model.Player
import blackjack.model.PlayerBehavior
import blackjack.model.PlayerBehavior.HIT
import blackjack.model.PlayerBehavior.STAY
import blackjack.model.Players

class InputView {
    fun readPlayers(): Players {
        val players =
            readln()
                .split(",")
                .map { Player(name = it.trim()) }
        return Players(players)
    }

    fun readPlayerBehavior(): PlayerBehavior {
        val answer = readln().trim().uppercase()
        return answer.toPlayerBehavior()
    }

    private fun String.toPlayerBehavior(): PlayerBehavior =
        when (this) {
            "Y" -> HIT
            "N" -> STAY
            else -> throw IllegalArgumentException("응답은 Y/N로만 가능합니다.")
        }
}
