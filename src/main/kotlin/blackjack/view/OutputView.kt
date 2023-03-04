package blackjack.view

import blackjack.domain.*
import blackjack.domain.CardNumber.*

object OutputView {
    private const val DEALER_INITIAL_CARD_SCRIPT = "딜러: %s"
    private const val DISTRIBUTE_SCRIPT = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val CAN_GET_CARD_SCRIPT = "딜러는 16이하라 한 장의 카드를 더 받았습니다."
    private const val CANNOT_GET_CARD_SCRIPT = "딜러는 17이상이라 카드를 더 받지 못합니다."
    private const val DEALER_WIN = 0
    private const val DEALER_LOSE = 1
    private const val DRAW = 2

    private fun makeCardToString(card: Card): String = stringOf(card.cardNumber) + card.shape.korean

    private fun makeBunchToString(bunch: CardBunch): String =
        bunch.cards.joinToString(separator = ", ") { makeCardToString(it) }

    fun printDealerInitialCard(cardBunch: CardBunch) {
        println(DEALER_INITIAL_CARD_SCRIPT.format(makeCardToString(cardBunch.cards.first())))
    }

    fun printPlayerInitialCard(player: Player) {
        val bunchString = makeBunchToString(player.cardBunch)
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

    fun printCardAndScore(dealer: Dealer, players: List<Player>) {
        println("딜러 카드 : ${makeBunchToString(dealer.cardBunch)} - 결과: ${dealer.cardBunch.getTotalScore()}")
        players.forEach { player ->
            val bunchString = makeBunchToString(player.cardBunch)
            println("${player.name}카드 : $bunchString - 결과: ${player.cardBunch.getTotalScore()}")
        }
        println()
    }

    fun printWinOrLose(referee: Referee) {
        println("##최종 승패")
        printPlayerResult(referee)
    }

    private fun printPlayerResult(referee: Referee) {
        val gameResult = referee.gameResult
        val dealerResult = makeDealerResult(referee)
        println("딜러: ${dealerResult[DEALER_WIN]}승 ${dealerResult[DEALER_LOSE]}패 ${dealerResult[DRAW]}무")
        gameResult.forEach {
            println("${it.key}: ${it.value.value}")
        }
    }

    private fun makeDealerResult(referee: Referee): List<Int> {
        val dealerResult = mutableListOf(0, 0, 0)
        val gameResult = referee.gameResult
        gameResult.forEach {
            when (it.value) {
                Consequence.WIN -> dealerResult[DEALER_LOSE]++
                Consequence.LOSE -> dealerResult[DEALER_WIN]++
                Consequence.DRAW -> dealerResult[DRAW]++
            }
        }
        return dealerResult
    }

    private fun stringOf(cardNumber: CardNumber): String {
        return when (cardNumber) {
            ACE -> "A"
            TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN -> cardNumber.value.toString()
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
        }
    }
}
