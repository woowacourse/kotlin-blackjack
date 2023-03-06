package blackjack.view

import blackjack.domain.BlackjackResult
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.ResultType

object ResultView {
    private const val SET_UP_MESSAGE = "\n%s와 %s에게 ${Participant.INIT_CARD_SIZE} 장의 카드를 나누었습니다."
    private const val FACE_UP_CARDS = "%s 카드: %s"
    private const val DEALER_HIT_MESSAGE = "\n%s는 ${Dealer.HIT_STANDARD_SCORE}이하라 한장의 카드를 더 받았습니다.\n"
    private const val SHOW_SCORE = " - 결과: %d"
    private const val FINAL_RESULT_MESSAGE = "\n## 최종 승패"
    private const val FINAL_RESULT = "%s:%s"
    private const val NOT_PARTICIPATE_PLAYER = "%s는 게임에 참여하지 않았습니다."

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

    fun printResult(dealer: Dealer, players: List<Player>, blackjackResult: BlackjackResult) {
        printCardsWithScore(dealer)
        players.forEach { printCardsWithScore(it) }

        println(FINAL_RESULT_MESSAGE)
        printDealerResult(blackjackResult, dealer)
        players.forEach {
            printPlayerResult(it, blackjackResult.getResultOf(it))
        }
    }

    private fun printCardsWithScore(participant: Participant) {
        println(participant.faceUp() + participant.showScore())
    }

    private fun printDealerResult(blackjackResult: BlackjackResult, dealer: Dealer) {
        val result = ResultType.values().fold("") { s, type ->
            s + type.toKorean(blackjackResult.getCountOfDealer(type))
        }
        println(FINAL_RESULT.format(dealer.name, result))
    }

    private fun printPlayerResult(player: Player, result: ResultType?) {
        result?.let { println(FINAL_RESULT.format(player.name, result.toKorean())) }
            ?: println(NOT_PARTICIPATE_PLAYER.format(player.name))
    }

    private fun Dealer.faceUpOnlyOne(): String = FACE_UP_CARDS.format(this.name, this.cards[0].name())
    private fun Participant.faceUp(): String = FACE_UP_CARDS.format(this.name, this.cards.joinToString(", ") { it.name() })
    private fun Participant.showScore(): String = SHOW_SCORE.format(this.getScore())
    private fun Card.name(): String = "${this.number.toMark()}${this.shape.toKorean()}"

    private fun CardNumber.toMark(): String =
        when (this) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            else -> this.value.toString()
        }

    private fun CardShape.toKorean(): String =
        when (this) {
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.HEART -> "하트"
            CardShape.CLOVER -> "클로버"
            CardShape.SPADE -> "스페이드"
        }

    private fun ResultType.toKorean(score: Int?): String {
        if (score == null || score <= 0) return ""
        return when (this) {
            ResultType.WIN -> " ${score}승"
            ResultType.TIE -> " ${score}무"
            ResultType.LOSE -> " ${score}패"
        }
    }

    private fun ResultType.toKorean(): String {
        return when (this) {
            ResultType.WIN -> " 승"
            ResultType.TIE -> " 무"
            ResultType.LOSE -> " 패"
        }
    }
}
