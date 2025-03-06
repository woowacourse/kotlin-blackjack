package blackjack.view

import blackjack.Card
import blackjack.Dealer
import blackjack.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.Player

class OutputView {
    fun printSetCardMessageWithPlayers(players: List<Player>) {
        val playerNames = players.joinToString(OUTPUT_SEPARATOR_FOR_PRINT) { player -> player.name }
        println(SET_CARD_MESSAGE_WITH_PLAYER_NAME_FORMAT.format(playerNames, INITIAL_HAND_OUT_CARD_COUNT))
    }

    fun printPlayerHands(player: Player) {
        println(HANDS_OF_PLAYER_FORMAT.format(player.name, getCardsStatus(player.cards)))
    }

    fun printAllPlayerHands(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(HANDS_OF_DEALER_FORMAT.format(dealer.name, getCardsStatus(listOf(dealer.cards.first()))))
        players.forEach { player -> printPlayerHands(player) }
        println()
    }

    private fun getCardsStatus(cards: List<Card>): String {
        return cards.joinToString(OUTPUT_SEPARATOR_FOR_PRINT) { card ->
            val cardShape = card.shape
            val cardNumber = card.number
            "${cardNumber.value}${cardShape.type}"
        }
    }

    companion object {
        private const val SET_CARD_MESSAGE_WITH_PLAYER_NAME_FORMAT = "\n딜러와 %s에게 %d장의 카드를 나누어 주었습니다."
        private const val OUTPUT_SEPARATOR_FOR_PRINT = ", "
        private const val HANDS_OF_DEALER_FORMAT = "%s 카드: %s"
        private const val HANDS_OF_PLAYER_FORMAT = "%s카드: %s"
    }
}
