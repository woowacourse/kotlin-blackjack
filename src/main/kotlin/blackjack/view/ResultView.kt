package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Dealer
import blackjack.domain.Dealer.Companion.HIT_STANDARD_SCORE
import blackjack.domain.Participant
import blackjack.domain.Participant.Companion.INIT_CARD_SIZE
import blackjack.domain.Player

object ResultView {
    private const val SET_UP_MESSAGE = "\n%s와 %s에게 $INIT_CARD_SIZE 장의 카드를 나누었습니다."
    private const val FACE_UP_CARDS = "%s 카드: %s"
    private const val DEALER_HIT_MESSAGE = "\n%s는 ${HIT_STANDARD_SCORE}이하라 한장의 카드를 더 받았습니다.\n"
    private const val SHOW_SCORE = " - 결과: %d"

    fun printSetUp(dealer: Dealer, players: List<Player>) {
        val names = players.joinToString(", ") { it.name }
        println(SET_UP_MESSAGE.format(dealer.name, names))
        println(dealer.faceUpOnlyOne())
        players.forEach { println(it.faceUp()) }
        println()
    }

    fun printCards(participant: Participant) {
        println(participant.faceUp())
    }

    fun printDealerHitMessage(name: String) {
        println(DEALER_HIT_MESSAGE.format(name))
    }

    fun printResult(dealer: Dealer, players: List<Player>) {
        printCardsWithScore(dealer, players)
    }

    private fun printCardsWithScore(dealer: Dealer, players: List<Player>) {
        println(dealer.faceUp() + dealer.showScore())
        players.forEach { println(it.faceUp() + it.showScore()) }
    }

    private fun Dealer.faceUpOnlyOne() = FACE_UP_CARDS.format(this.name, this.cards[0].name())
    private fun Participant.faceUp() = FACE_UP_CARDS.format(this.name, this.cards.joinToString(", ") { it.name() })
    private fun Participant.showScore() = SHOW_SCORE.format(this.getScore())
    private fun Card.name() = "${this.number.toMark()}${this.shape.toKorean()}"

    private fun CardNumber.toMark() =
        when (this) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            else -> this.value.toString()
        }

    private fun CardShape.toKorean() =
        when (this) {
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.HEART -> "하트"
            CardShape.CLOVER -> "클로버"
            CardShape.SPADE -> "스페이드"
        }
}
