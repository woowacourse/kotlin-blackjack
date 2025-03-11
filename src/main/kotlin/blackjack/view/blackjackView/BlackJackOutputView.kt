package blackjack.view.blackjackView

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Player

object BlackJackOutputView {
    fun showInitialCards(game: BlackJackGame) {
        println(printProvidedCard(game))
        println(printDealerCard(game.dealer))
        game.players.forEach { player ->
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

    private fun printProvidedCard(game: BlackJackGame): String = "딜러와 ${game.players.joinToString { it.name }}에게 2장을 나누었습니다.\n"

    private fun printDealerCard(dealer: Dealer): String = "딜러: ${dealer.cards.toList().first().format()}"

    private fun printPlayerCard(player: Player): String = "${player.name}카드: ${player.cards.format()}"
}
