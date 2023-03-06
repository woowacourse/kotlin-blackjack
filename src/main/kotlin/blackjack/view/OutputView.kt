package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardBunch
import blackjack.domain.CardNumber
import blackjack.domain.Consequence
import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.domain.Player

object OutputView {

    private const val DEALER_INITIAL_CARD_SCRIPT = "딜러: %s"
    private const val DISTRIBUTE_SCRIPT = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val CAN_GET_CARD_SCRIPT = "딜러는 16이하라 한 장의 카드를 더 받았습니다."
    private const val CANNOT_GET_CARD_SCRIPT = "딜러는 17이상이라 카드를 더 받지 못합니다."
    private const val DEALER_WIN = 0
    private const val DEALER_LOSE = 1
    private const val DRAW = 2
    private fun makeCardString(card: Card): String = CardNumber.valueOf(card.cardNumber) + card.shape.korean

    private fun makeBunchString(bunch: CardBunch): String =
        bunch.cards.joinToString(separator = ", ") { makeCardString(it) }

    fun printDealerInitialCard(cardBunch: CardBunch) {
        println(DEALER_INITIAL_CARD_SCRIPT.format(makeCardString(cardBunch.cards.first())))
    }

    fun printPlayerCards(player: Player) {
        val bunchString = makeBunchString(player.cardBunch)
        println("${player.name}카드 : $bunchString")
    }

    fun printDistributeScript(players: List<Player>) {
        println(DISTRIBUTE_SCRIPT.format(players.joinToString(separator = ", ") { it.name }))
    }

    fun printDealerOverCondition(condition: Boolean) {
        when (condition) {
            true -> println(CAN_GET_CARD_SCRIPT)
            false -> println(CANNOT_GET_CARD_SCRIPT)
        }
    }

    fun printTotalScore(participants: Participants) {
        println("딜러 카드 : ${makeBunchString(participants.dealer.cardBunch)} - 결과: ${participants.dealer.cardBunch.getTotalScore()}")
        participants.players.forEach { player ->
            val bunchString = makeBunchString(player.cardBunch)
            println("${player.name}카드 : $bunchString - 결과: ${player.cardBunch.getTotalScore()}")
        }
        println()
    }

    fun printWinOrLose(players: List<Player>) {
        println("##최종 승패")
        printDealerResult(players)
        getResultString(players)
    }

    private fun printDealerResult(players: List<Player>) {
        val result = mutableListOf(0, 0, 0)
        players.forEach { player ->
            result[decideDealerResult(player)]++
        }
        println("딜러: ${result[DEALER_WIN]}승 ${result[DEALER_LOSE]}패 ${result[DRAW]}무")
    }

    private fun decideDealerResult(player: Player): Int =
        when (player.consequence) {
            Consequence.WIN -> DEALER_LOSE
            Consequence.LOSE -> DEALER_WIN
            Consequence.DRAW -> DRAW
        }

    private fun getResultString(players: List<Player>) {
        var resultString = ""
        players.forEach { player ->
            resultString += decidePlayerResult(player)
        }
        println(resultString)
    }

    private fun decidePlayerResult(player: Player) = when (player.chooseWinner()) {
        Consequence.WIN -> "${player.name}: 승\n"
        Consequence.LOSE -> "${player.name}: 패\n"
        Consequence.DRAW -> "${player.name}: 무\n"
    }
}
