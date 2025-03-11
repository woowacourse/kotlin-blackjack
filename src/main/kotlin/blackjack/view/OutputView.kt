package blackjack.view

import blackjack.domain.Card
import blackjack.domain.GameResultStatus
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

object OutputView {
    fun showInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러와 ${players.joinToString { it.name }}에게 2장을 나누었습니다.\n")
        println("딜러: ${printFormattedCard(dealer.hand.getCards().first())}")
        players.forEach { player ->
            printPlayerCards(player)
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${printCardList(player.hand.getCards())}")
    }

    fun printDealerHaveAdditionalCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printFinalCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러 카드: ${printCardList(dealer.hand.getCards())} - 결과: ${dealer.totalSum}")

        players.forEach { player ->
            println("${player.name}카드: ${printCardList(player.hand.getCards())} - 결과: ${player.totalSum}")
        }
    }

    fun printGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("\n##최종 승패")
        println("딜러: ${dealer.result.win}승 ${dealer.result.lose}패 ${dealer.result.draw}무")

        players.forEach { player ->
            println("${player.name}: ${player.result.win}승 ${player.result.lose}패 ${player.result.draw}무")
        }
    }

    private fun printCardList(cards: List<Card>): String {
        return cards.joinToString(", ") { printFormattedCard(it) }
    }

    private fun printFormattedCard(card: Card): String {
        return card.rank.toDisplayName() + card.suit.toDisplayName()
    }

    private fun Rank.toDisplayName(): String =
        when (this) {
            Rank.ACE -> "A"
            Rank.TWO -> "2"
            Rank.THREE -> "3"
            Rank.FOUR -> "4"
            Rank.FIVE -> "5"
            Rank.SIX -> "6"
            Rank.SEVEN -> "7"
            Rank.EIGHT -> "8"
            Rank.NINE -> "9"
            Rank.TEN -> "10"
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
        }

    private fun Suit.toDisplayName(): String =
        when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }

    private fun GameResultStatus.toDisplayName(): String =
        when (this) {
            GameResultStatus.PLAYER_WIN -> "승"
            GameResultStatus.PLAYER_LOSE -> "패"
            GameResultStatus.DRAW -> "무"
        }
}
