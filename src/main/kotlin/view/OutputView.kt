package view

import domain.card.Card
import domain.gamer.state.ParticipantState
import domain.gamer.state.PlayerState
import domain.judge.Result

object OutputView {

    fun printDivideCard(names: List<String>) {
        println()
        println(names.joinToString(", ", "딜러와 ", "에게 2장을 나누었습니다."))
    }

    fun printDealerSettingCard(card: Card) {
        println("딜러: ${printCardForm(card)}")
    }

    fun printParticipantsCards(participants: Map<String, PlayerState>) {
        participants.forEach { it ->
            printParticipantCards(it.key, it.value.cards)
        }
        println()
    }

    fun printParticipantCards(name: String, cards: List<Card>) {
        println("${name}카드: ${cards.joinToString(", ") { printCardForm(it) }}")
    }

    private fun printCardForm(card: Card): String {
        return card.cardValue.title + card.shape.shape
    }

    fun printDealerUnder16() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printCardResult(participants: Map<String, ParticipantState>) {
        participants.forEach { name, participant ->
            println("${name}카드: ${participant.cards.joinToString(", ") { printCardForm(it) }} - 결과: ${participant.calculateCardSum()}")
        }
    }

    fun printWinningResult(dealerResult: List<Result>, playerStates: Map<String, Result>) {
        println("\n## 최종 승패")
        printDealerWinningResult(dealerResult)
        printPlayerWinningResult(playerStates)
    }

    private fun printDealerWinningResult(dealerResult: List<Result>) {
        val winCount = formatResultCount(dealerResult.count { it == Result.WIN }, Result.WIN)
        val lossCount = formatResultCount(dealerResult.count { it == Result.LOSS }, Result.LOSS)
        val drawCount = formatResultCount(dealerResult.count { it == Result.DRAW }, Result.DRAW)
        println("딜러: $winCount $lossCount $drawCount")
    }

    private fun formatResultCount(count: Int, result: Result) = if (count == 0) "" else count.toString() + result.result

    private fun printPlayerWinningResult(playerResult: Map<String, Result>) {
        playerResult.forEach {
            println("${it.key}: ${it.value.result}")
        }
    }
}
