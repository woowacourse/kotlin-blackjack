package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

class InputView {
    fun readPlayers(): Players {
        val players =
            readln()
                .split(",")
                .map { Player(name = it.trim()) }
        return Players(players)
    }

    fun readPlayerBehavior(): Player.Behavior {
        val answer = readln().trim().uppercase()
        return answer.toPlayerBehavior()
    }

    private fun String.toPlayerBehavior(): Player.Behavior =
        when (this) {
            "Y" -> Player.Behavior.HIT
            "N" -> Player.Behavior.STAY
            else -> throw IllegalArgumentException("응답은 Y/N로만 가능합니다.")
        }
}
