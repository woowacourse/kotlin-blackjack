package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit

class OutputView {
    fun printDealingResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val playerNames = players.joinToString { it.name }
        println(MESSAGE_DEALING.format(playerNames))

        val dealerCard = dealer.state.hand.cards
        println(MESSAGE_DEALER_CARD.format(cardsInfo(dealerCard)))
        players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        val playerCards = cardsInfo(player.state.hand.cards)
        println(MESSAGE_PLAYER_CARD.format(player.name, playerCards))
    }

    fun printBust(player: Player) {
        println(MESSAGE_BUST.format(player.name))
    }

    fun printDealerHit(hitCount: Int) {
        println(MESSAGE_DEALER_HIT.format(hitCount))
    }

    fun printBlackjackScore(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerCards = cardsInfo(dealer.state.hand.cards)
        val dealerScore = dealer.state.hand.sum()
        println("${MESSAGE_DEALER_CARD.format(dealerCards)} ${MESSAGE_SCORE.format(dealerScore)}")

        players.forEach { player ->
            val playerCards = cardsInfo(player.state.hand.cards)
            val playerScore = player.state.hand.sum()
            println(
                "${MESSAGE_PLAYER_CARD.format(player.name, playerCards)} ${
                    MESSAGE_SCORE.format(
                        playerScore,
                    )
                }",
            )
        }
    }

    fun printPlayerProfit(
        player: Player,
        profit: Int,
    ) {
        println(MESSAGE_PLAYER_RESULT.format(player.name, profit))
    }

    fun printDealerProfit(profit: Int) {
        println(MESSAGE_GAME_RESULT)
        println(MESSAGE_DEALER_RESULT.format(profit))
    }

    private fun cardsInfo(cards: List<Card>): String = cards.joinToString { cardInfo(it) }

    private fun cardInfo(card: Card): String {
        val denomination = denomination(card.denomination)
        val suit = suit(card.suit)
        return "$denomination$suit"
    }

    private fun suit(suit: Suit) =
        when (suit) {
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
            Suit.HEART -> "하트"
            Suit.SPADE -> "스페이드"
        }

    private fun denomination(denomination: Denomination) =
        when (denomination) {
            Denomination.ACE -> "A"
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
            else -> denomination.value
        }

    companion object {
        private const val MESSAGE_DEALING = "\n딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val MESSAGE_BUST = "%s는 더 이상 카드를 받을 수 없습니다."
        private const val MESSAGE_DEALER_CARD = "딜러 카드: %s"
        private const val MESSAGE_PLAYER_CARD = "%s 카드: %s"
        private const val MESSAGE_SCORE = "- 결과: %d"
        private const val MESSAGE_DEALER_HIT = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n"
        private const val MESSAGE_GAME_RESULT = "\n## 최종 수익"
        private const val MESSAGE_DEALER_RESULT = "\n딜러: %d"
        private const val MESSAGE_PLAYER_RESULT = "%s: %d"
    }
}
