package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardBunch
import blackjack.domain.CardNumber
import blackjack.domain.Consequence
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
    private fun makeCardString(card: Card): String = getCardNumberString(card.cardNumber) + card.shape.korean

    private fun makeBunchString(bunch: CardBunch): String =
        bunch.cards.joinToString(separator = ", ") { makeCardString(it) }

    fun printDealerInitialCard(cardBunch: CardBunch) {
        println(DEALER_INITIAL_CARD_SCRIPT.format(makeCardString(cardBunch.cards.first())))
    }

    fun printPlayerCards(player: Player) {
        val bunchString = makeBunchString(player.cardBunch)
        println("${player.name}카드 : $bunchString")
    }

    fun printAllPlayerCard(players: List<Player>) {
        players.forEach { printPlayerCards(it) }
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
        participants.players.value.forEach { player ->
            val bunchString = makeBunchString(player.cardBunch)
            println("${player.name}카드 : $bunchString - 결과: ${player.cardBunch.getTotalScore()}")
        }
        println()
    }

    fun printWinOrLose(participants: Participants) {
        println("##최종 승패")
        printDealerResult(participants)
        getResultString(participants)
    }

    private fun printDealerResult(participants: Participants) {
        val result = mutableListOf(0, 0, 0)
        participants.players.value.forEach { player ->
            result[decideDealerResult(participants.getConsequence(player))]++
        }
        println("딜러: ${result[DEALER_WIN]}승 ${result[DEALER_LOSE]}패 ${result[DRAW]}무")
    }

    private fun decideDealerResult(consequence: Consequence): Int =
        when (consequence) {
            Consequence.WIN -> DEALER_LOSE
            Consequence.LOSE -> DEALER_WIN
            Consequence.DRAW -> DRAW
        }

    private fun getResultString(participants: Participants) {
        var resultString = ""
        participants.players.value.forEach { player ->
            resultString += decidePlayerResult(participants.getConsequence(player), player)
        }
        println(resultString)
    }

    private fun decidePlayerResult(consequence: Consequence, player: Player) = when (consequence) {
        Consequence.WIN -> "${player.name}: 승\n"
        Consequence.LOSE -> "${player.name}: 패\n"
        Consequence.DRAW -> "${player.name}: 무\n"
    }

    private fun getCardNumberString(cardNumber: CardNumber): String {
        return when (cardNumber) {
            CardNumber.ACE -> "A"
            CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR, CardNumber.FIVE, CardNumber.SIX, CardNumber.SEVEN, CardNumber.EIGHT, CardNumber.NINE, CardNumber.TEN -> cardNumber.value.toString()
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }
    }
}
