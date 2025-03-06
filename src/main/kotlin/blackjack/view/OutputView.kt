package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player

class OutputView {
    fun printDealingResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val playerNames = players.joinToString(SEPARATOR) { it.name }
        println(MESSAGE_DEALING.format(playerNames))

        val dealerCard = dealer.hand.cards.first()
        println(MESSAGE_DEALER_CARD.format(cardInfo(dealerCard)))
        players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        val playerCards = player.hand.cards.joinToString(SEPARATOR) { cardInfo(it) }
        println(MESSAGE_PLAYER_CARD.format(player.name, playerCards))
    }

    fun printDealerHit() {
        println(MESSAGE_DEALER_HIT)
    }

    fun printBlackjackResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerCards = dealer.hand.cards.joinToString(SEPARATOR) { cardInfo(it) }
        val dealerScore = dealer.calculateScore()
        println("${MESSAGE_DEALER_CARD.format(dealerCards)} ${MESSAGE_SCORE.format(dealerScore)}")

        players.forEach { player ->
            val playerCards = player.hand.cards.joinToString(SEPARATOR) { cardInfo(it) }
            val playerScore = player.calculateScore()
            println("${MESSAGE_PLAYER_CARD.format(player.name, playerCards)} ${MESSAGE_SCORE.format(playerScore)}")
        }
    }

    private fun cardInfo(card: Card): String {
        val number = card.rank.symbol
        val shape = card.suit.korean
        return "$number$shape"
    }

    companion object {
        private const val MESSAGE_DEALING = "\n딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_DEALER_CARD = "딜러 카드: %s"
        private const val MESSAGE_PLAYER_CARD = "%s 카드: %s"
        private const val MESSAGE_SCORE = "- 결과: %d"
        private const val MESSAGE_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
        private const val SEPARATOR = ", "
    }
}
