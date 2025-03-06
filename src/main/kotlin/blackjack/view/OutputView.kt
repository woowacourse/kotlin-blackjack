package blackjack.view

import blackjack.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.Player

class OutputView {
    fun printSetCardMessageWithPlayers(players: List<Player>) {
        val playerNames = players.joinToString(OUTPUT_SEPARATOR_FOR_PRINT) { player -> player.name }
        println(SET_CARD_MESSAGE_WITH_PLAYER_NAME_FORMAT.format(playerNames, INITIAL_HAND_OUT_CARD_COUNT))
    }

    companion object {
        private const val SET_CARD_MESSAGE_WITH_PLAYER_NAME_FORMAT = "\n딜러와 %s에게 %d장의 카드를 나누어 주었습니다."
        private const val OUTPUT_SEPARATOR_FOR_PRINT = ", "
    }
}