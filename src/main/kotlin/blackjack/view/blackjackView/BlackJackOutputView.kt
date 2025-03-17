package blackjack.view.blackjackView

import blackjack.domain.participant.BlackJackPair
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

object BlackJackOutputView {
    fun showInitialCards(pair: BlackJackPair) {
        println(printProvidedCard(pair))
        println(printDealerCard(pair.dealer))
        pair.players.forEach { player ->
            printPlayerCards(player)
        }
    }

    fun printPlayerCards(player: Player) {
        println(printPlayerCard(player))
    }

    fun printDealerHaveAdditionalCard() {
        println(DEALER_GET_ADDITIONAL_CARD)
    }

    private const val DEALER_GET_ADDITIONAL_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"

    private fun printProvidedCard(pair: BlackJackPair): String = "딜러와 ${pair.players.joinToString { it.name }}에게 2장을 나누었습니다.\n"

    private fun printDealerCard(dealer: Dealer): String = "딜러: ${dealer.getCards().first().format()}"

    private fun printPlayerCard(player: Player): String = "${player.name}카드: ${player.getCards().format()}"
}
