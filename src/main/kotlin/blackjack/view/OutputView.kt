package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardBunch
import blackjack.domain.CardNumber
import blackjack.domain.Consequence
import blackjack.domain.Dealer
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

    fun printTotalScore(dealer: Dealer, players: List<Player>) {
        println("딜러 카드 : ${makeBunchString(dealer.cardBunch)} - 결과: ${dealer.cardBunch.getTotalScore()}")
        players.forEach { player ->
            val bunchString = makeBunchString(player.cardBunch)
            println("${player.name}카드 : $bunchString - 결과: ${player.cardBunch.getTotalScore()}")
        }
        println()
    }

    fun printResult(players: List<Player>) {
        val result = mutableListOf(0, 0, 0)
        var resultString = ""
        println("##최종 승패")
        players.forEach { player ->
            when (player.consequence) {
                Consequence.WIN -> {
                    resultString += "${player.name}: 승\n"
                    result[DEALER_LOSE]++
                }

                Consequence.LOSE -> {
                    resultString += "${player.name}: 패\n"
                    result[DEALER_WIN]++
                }

                Consequence.DRAW -> {
                    resultString += "${player.name}: 무\n"
                    result[DRAW]++
                }
            }
        }
        println("딜러: ${result[DEALER_WIN]}승 ${result[DEALER_LOSE]}패 ${result[DRAW]}무")
        println(resultString)
    }
}
