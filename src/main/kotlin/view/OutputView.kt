package view

import domain.card.Card
import domain.card.Shape
import domain.gamer.Participant
import domain.gamer.Player
import domain.gamer.cards.Cards
import domain.judge.Result

object OutputView {
    private const val SEPARATOR = ", "
    private const val WITH_DEALER = "딜러와 "
    private const val DIVIDE_TWO_CARDS = "에게 2장을 나누었습니다."

    private const val DEALER = "딜러: "
    private const val PARTICIPANT_CARD = "카드:"
    private const val RESULT = " - 결과: "
    private const val FINAL_RESULT = "\n## 최종 승패"
    private const val PICK_CARD_OVER_SIXTEEN = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
    private const val FINAL_REVENUE = "\n## 최종 수익"
    private const val REVENUE_FORM = "%s: %d"

    fun printDivideCard(names: List<String>) {
        println()
        println(names.joinToString(SEPARATOR, WITH_DEALER, DIVIDE_TWO_CARDS))
    }

    fun printDealerSettingCard(card: Card) {
        println(DEALER + printCardForm(card))
    }

    fun printParticipantsCards(participants: List<Player>) {
        participants.forEach { it ->
            printParticipantCards(it.name, it.cards)
        }
        println()
    }

    fun printParticipantCards(name: String, cards: Cards) {
        println("${name}$PARTICIPANT_CARD ${cards.getCards().joinToString(SEPARATOR) { printCardForm(it) }}")
    }

    private fun printCardForm(card: Card): String {
        return card.cardValue.title + printCardShape(card.shape)
    }

    private fun printCardShape(cardShape: Shape): String {
        return when (cardShape) {
            Shape.HEART -> "하트"
            Shape.CLOVER -> "클로버"
            Shape.DIAMOND -> "다이아몬드"
            Shape.SPADE -> "스페이드"
        }
    }

    fun printDealerUnder16() {
        println(PICK_CARD_OVER_SIXTEEN)
    }

    fun printCardResult(participants: Map<String, Participant>) {
        participants.forEach { (name, participant) ->
            println("${name}$PARTICIPANT_CARD ${participant.cards.getCards().joinToString(SEPARATOR) { printCardForm(it) }}${RESULT}${participant.cards.calculateCardSum()}")
        }
    }

    fun printWinningResult(dealerResult: List<Result>, playerStates: Map<Player, Result>) {
        println(FINAL_RESULT)
        printDealerWinningResult(dealerResult)
        printPlayerWinningResult(playerStates)
    }

    private fun printDealerWinningResult(dealerResult: List<Result>) {
        val winCount = formatResultCount(dealerResult.count { it == Result.WIN }, Result.WIN)
        val lossCount = formatResultCount(dealerResult.count { it == Result.LOSS }, Result.LOSS)
        val drawCount = formatResultCount(dealerResult.count { it == Result.DRAW }, Result.DRAW)
        println("$DEALER$winCount $lossCount $drawCount")
    }

    private fun formatResultCount(count: Int, result: Result) =
        if (count == 0) "" else count.toString() + printResultForm(result)

    private fun printPlayerWinningResult(playerResult: Map<Player, Result>) {
        playerResult.forEach {
            println("${it.key.name}: ${printResultForm(it.value)}")
        }
    }

    private fun printResultForm(result: Result): String {
        return when (result) {
            Result.WIN -> "승"
            Result.DRAW -> "무"
            Result.LOSS -> "패"
        }
    }

    fun printRevenue(dealerRevenue: Int, playerRevenue: Map<String, Int>) {
        println(FINAL_REVENUE)
        println(DEALER + "%d".format(dealerRevenue))
        playerRevenue.map {
            println(REVENUE_FORM.format(it.key, it.value))
        }
    }
}
