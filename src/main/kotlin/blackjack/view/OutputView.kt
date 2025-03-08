package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.enums.Rank
import blackjack.enums.Result
import blackjack.enums.Suit

class OutputView {
    fun printCardInfo(
        dealer: Dealer,
        players: Players,
    ) {
        val playerNames = players.players.joinToString { it.name }
        println(MESSAGE_DEALING.format(playerNames))

        val dealerCard = dealer.hand.cards.first()
        println(MESSAGE_CARD_INFO.format(dealer.name, dealerCard.toText()))
        players.players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        println(cardsInfo(player))
    }

    fun printBust(player: Player) {
        println(MESSAGE_BUST.format(player.name))
    }

    fun printDealerHit(
        dealer: Dealer,
        count: Int,
    ) {
        println(MESSAGE_DEALER_HIT.format(dealer.name, count))
    }

    fun printParticipantScore(
        dealer: Dealer,
        players: Players,
    ) {
        println("${cardsInfo(dealer)} ${MESSAGE_SCORE.format(dealer.getScore().score)}")
        players.players.forEach {
            println("${cardsInfo(it)} ${MESSAGE_SCORE.format(it.getScore().score)}")
        }
    }

    fun printDealerResult(
        dealer: Dealer,
        dealerResult: Map<Result, Int>,
    ) {
        println(MESSAGE_GAME_RESULT)
        val order = listOf(Result.WIN, Result.PUSH, Result.LOSE)
        val joinedResult =
            order
                .mapNotNull { result -> dealerResult[result]?.let { "$it${result.toText()}" } }
                .joinToString(" ")
        println(MESSAGE_RESULT.format(dealer.name, joinedResult))
    }

    fun printPlayerResult(
        name: String,
        result: Result,
    ) {
        println(MESSAGE_RESULT.format(name, result.toText()))
    }

    private fun cardsInfo(participant: Participant): String {
        val joinedCards = participant.hand.cards.joinToString { it.toText() }
        return MESSAGE_CARD_INFO.format(participant.name, joinedCards)
    }

    companion object {
        private const val MESSAGE_DEALING = "\n딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_BUST = "%s는 더 이상 카드를 받을 수 없습니다."
        private const val MESSAGE_CARD_INFO = "%s 카드: %s"
        private const val MESSAGE_SCORE = "- 결과: %d"
        private const val MESSAGE_DEALER_HIT = "\n%s는 16이하라 %d장의 카드를 더 받았습니다.\n"
        private const val MESSAGE_GAME_RESULT = "\n## 최종 승패"
        private const val MESSAGE_RESULT = "%s: %s"

        private fun Rank.toText(): String =
            when (this) {
                Rank.ACE -> "A"
                Rank.JACK -> "J"
                Rank.QUEEN -> "Q"
                Rank.KING -> "K"
                else -> this.number.toString()
            }

        private fun Suit.toText(): String =
            when (this) {
                Suit.DIAMOND -> "다이아몬드"
                Suit.CLUB -> "클로버"
                Suit.HEART -> "하트"
                Suit.SPADE -> "스페이드"
            }

        private fun Card.toText(): String = "${this.rank.toText()}${this.suit.toText()}"

        private fun Result.toText(): String =
            when (this) {
                Result.WIN -> "승"
                Result.LOSE -> "패"
                Result.PUSH -> "무"
            }
    }
}
