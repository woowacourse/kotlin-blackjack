package blackjack.view

import blackjack.domain.betting.BettingResult
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

object ResultView {
    private const val SET_UP_MESSAGE = "\n%s와 %s에게 ${Participant.INIT_CARD_SIZE} 장의 카드를 나누었습니다."
    private const val FACE_UP_CARDS = "%s 카드: %s"
    private const val DEALER_HIT_MESSAGE = "\n%s는 ${Dealer.HIT_STANDARD_SCORE}이하라 한장의 카드를 더 받았습니다.\n"
    private const val SHOW_SCORE = " - 결과: %d"
    private const val FINAL_RESULT_MESSAGE = "\n## 최종 수익"
    private const val FINAL_RESULT = "%s: %s"

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

    fun printResult(dealer: Dealer, players: List<Player>, bettingResult: BettingResult) {
        printCardsWithScore(dealer)
        players.forEach { printCardsWithScore(it) }

        println(FINAL_RESULT_MESSAGE)
        printDealerResult(bettingResult, dealer)
        players.forEach {
            printPlayerResult(bettingResult, it)
        }
    }

    private fun printCardsWithScore(participant: Participant) {
        println(participant.faceUp() + participant.showScore())
    }

    private fun printDealerResult(bettingResult: BettingResult, dealer: Dealer) {
        val result = bettingResult.getDealerEarningMoney()
        println(FINAL_RESULT.format(dealer.name, result))
    }

    private fun printPlayerResult(bettingResult: BettingResult, player: Player) {
        println(FINAL_RESULT.format(player.name, bettingResult.getPlayerEarningMoney(player)))
    }

    private fun Dealer.faceUpOnlyOne(): String = FACE_UP_CARDS.format(this.name, this.cards[0].name())
    private fun Participant.faceUp(): String =
        FACE_UP_CARDS.format(this.name, this.cards.joinToString(", ") { it.name() })

    private fun Participant.showScore(): String = SHOW_SCORE.format(this.score)
    private fun Card.name(): String = "${this.number.toMark()}${this.shape.toKorean()}"

    private fun CardNumber.toMark(): String =
        when (this) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            CardNumber.TWO,
            CardNumber.THREE,
            CardNumber.FOUR,
            CardNumber.FIVE,
            CardNumber.SIX,
            CardNumber.SEVEN,
            CardNumber.EIGHT,
            CardNumber.NINE,
            CardNumber.TEN -> this.value.toString()
        }

    private fun CardShape.toKorean(): String =
        when (this) {
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.HEART -> "하트"
            CardShape.CLOVER -> "클로버"
            CardShape.SPADE -> "스페이드"
        }
}
