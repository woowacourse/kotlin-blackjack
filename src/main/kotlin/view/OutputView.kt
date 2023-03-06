package view

import domain.card.Card
import domain.judge.ParticipantResult
import domain.judge.Result
import domain.player.Names
import domain.player.Player

object OutputView {
    private const val SEPARATOR = ", "
    private const val WITH_DEALER = "딜러와 "
    private const val DIVIDE_TWO_CARDS = "에게 2장을 나누었습니다."

    private const val DEALER = "딜러: "
    private const val PARTICIPANT_CARD = "카드:"
    private const val RESULT = " - 결과: "
    private const val FINAL_RESULT = "\n## 최종 승패"
    private const val PICK_CARD_OVER_SIXTEEN = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"

    fun printDivideCard(names: Names) {
        println()
        println(names.userNames.joinToString(SEPARATOR, WITH_DEALER, DIVIDE_TWO_CARDS))
    }

    fun printDealerSettingCard(card: Card) {
        println(DEALER + printCardForm(card))
    }

    fun printParticipantsCards(participants: List<Player>) {
        participants.forEach { it ->
            printParticipantCards(it.name, it.ownCards.cards)
        }
        println()
    }

    fun printParticipantCards(name: String, cards: List<Card>) {
        println("${name}$PARTICIPANT_CARD ${cards.joinToString(SEPARATOR) { printCardForm(it) }}")
    }

    private fun printCardForm(card: Card): String {
        return card.cardValue.title + card.shape.shape
    }

    fun printDealerUnder16() {
        println(PICK_CARD_OVER_SIXTEEN)
    }

    fun printCardResult(participants: List<Player>) {
        participants.forEach { (name, participant) ->
            println("${name}$PARTICIPANT_CARD ${participant.cards.joinToString(SEPARATOR) { printCardForm(it) }}${RESULT}${participant.calculateCardSum()}")
        }
    }

    fun printWinningResult(dealerResult: List<Result>, playerStates: List<ParticipantResult>) {
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

    private fun formatResultCount(count: Int, result: Result) = if (count == 0) "" else count.toString() + result.result

    private fun printPlayerWinningResult(playerResult: List<ParticipantResult>) {
        playerResult.forEach {
            println("${it.name}: ${it.result.result}")
        }
    }
}
