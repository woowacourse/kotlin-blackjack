package blackjack.view

import blackjack.domain.*

object ResultView {
    private const val SET_UP_MESSAGE = "\n%s와 %s에게 ${Participant.INIT_CARD_SIZE} 장의 카드를 나누었습니다."
    private const val FACE_UP_CARDS = "%s 카드: %s"
    private const val DEALER_HIT_MESSAGE = "\n%s는 ${Dealer.HIT_STANDARD_SCORE}이하라 한장의 카드를 더 받았습니다.\n"
    private const val SHOW_SCORE = " - 결과: %d"
    private const val FINAL_RESULT_MESSAGE = "\n## 최종 승패"
    private const val FINAL_RESULT = "%s:%s"
    private const val NOT_PARTICIPATE_PLAYER = "%s는 게임에 참여하지 않았습니다."

    fun printSetUp(dealer: Dealer, players: Players) {
        val playerList = players.toList()
        val names = playerList.joinToString(", ") { it.name }
        println(SET_UP_MESSAGE.format(dealer.name, names))
        println(dealer.faceUpOnlyOne())
        playerList.forEach { printCards(it) }
        println()
    }

    fun printCards(participant: Participant) {
        println(participant.faceUp())
    }

    fun printDealerHitMessage(name: String) {
        println(DEALER_HIT_MESSAGE.format(name))
    }

    fun printResult(dealer: Dealer, players: Players, blackjackResult: BlackjackResult) {
        printCardsWithScore(dealer)
        players.forEach { printCardsWithScore(it) }

        println(FINAL_RESULT_MESSAGE)
        printDealerResult(dealer, blackjackResult)
        players.forEach {
            printPlayerResult(it, blackjackResult.getResultOf(it))
        }
    }

    private fun printCardsWithScore(participant: Participant) {
        println(participant.faceUp() + participant.showScore())
    }

    private fun printDealerResult(dealer: Dealer, blackjackResult: BlackjackResult) {
        val result = ResultType.values().fold("") { s, type ->
            s + type.toTextWithCount(blackjackResult.getCountOfDealer(type))
        }
        println(FINAL_RESULT.format(dealer.name, result))
    }

    private fun printPlayerResult(player: Player, result: ResultType?) {
        result?.let { println(FINAL_RESULT.format(player.name, result.toText())) }
            ?: println(NOT_PARTICIPATE_PLAYER.format(player.name))
    }

    private fun Dealer.faceUpOnlyOne(): String = FACE_UP_CARDS.format(this.name, this.cards[0].name())
    private fun Participant.faceUp(): String =
        FACE_UP_CARDS.format(this.name, this.cards.joinToString(", ") { it.name() })

    private fun Participant.showScore(): String = SHOW_SCORE.format(this.getScore())
    private fun Card.name(): String = "${this.number.toMark()}${this.shape.toText()}"

    private fun CardNumber.toMark(): String =
        when (this) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            else -> this.value.toString()
        }

    private fun CardShape.toText(): String =
        when (this) {
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.HEART -> "하트"
            CardShape.CLOVER -> "클로버"
            CardShape.SPADE -> "스페이드"
        }

    private fun ResultType.toTextWithCount(count: Int?): String {
        if (count == null || count <= 0) return ""
        return when (this) {
            ResultType.WIN -> " ${count}승"
            ResultType.TIE -> " ${count}무"
            ResultType.LOSE -> " ${count}패"
        }
    }

    private fun ResultType.toText(): String {
        return when (this) {
            ResultType.WIN -> " 승"
            ResultType.TIE -> " 무"
            ResultType.LOSE -> " 패"
        }
    }

    fun printMessage(message: String) {
        println(message)
    }

    fun printErrorMessage(error: Throwable) {
        println(error.message)
    }
}
