package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardBunch
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Shape

object OutputView {
    private const val DEALER_INITIAL_CARD_SCRIPT = "딜러: %s"
    private const val DISTRIBUTE_SCRIPT = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val CAN_GET_CARD_SCRIPT = "딜러는 16이하라 한 장의 카드를 더 받았습니다."
    private const val CANNOT_GET_CARD_SCRIPT = "딜러는 17이상이라 카드를 더 받지 못합니다."

    private fun makeCardToString(card: Card): String {
        return stringOfNumber(card.cardNumber) + stringOfShape(card.shape)
    }

    private fun makeBunchToString(bunch: CardBunch): String {
        return bunch.cards.joinToString(separator = ", ") { makeCardToString(it) }
    }

    fun printDealerInitialCard(cardBunch: CardBunch) {
        println(DEALER_INITIAL_CARD_SCRIPT.format(makeCardToString(cardBunch.cards.first())))
    }

    fun printPlayerCard(player: Player) {
        val bunchString = makeBunchToString(player.cardBunch)
        println("${player.name}카드 : $bunchString")
    }

    fun printDistributeScript(players: List<Player>) {
        println(DISTRIBUTE_SCRIPT.format(players.joinToString(separator = ", ") { it.name }))
    }

    fun printDealerState(condition: Boolean) {
        when (condition) {
            true -> println(CAN_GET_CARD_SCRIPT)
            false -> println(CANNOT_GET_CARD_SCRIPT)
        }
    }

    fun printCardAndScore(dealer: Dealer, players: List<Player>) {
        println("딜러 카드 : ${makeBunchToString(dealer.cardBunch)} - 결과: ${dealer.getScore()}")
        players.forEach { player ->
            val bunchString = makeBunchToString(player.cardBunch)
            println("${player.name}카드 : $bunchString - 결과: ${player.getScore()}")
        }
        println()
    }

    fun printProfit(playerProfitBy: Map<Player, Int>) {
        val dealerProfit = playerProfitBy.values.sum() * -1
        println("딜러: $dealerProfit")
        playerProfitBy.keys.forEach { player ->
            println("${player.name}: ${playerProfitBy[player]}")
        }
    }

    private fun stringOfNumber(cardNumber: CardNumber): String {
        return when (cardNumber) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            else -> cardNumber.value.toString()
        }
    }

    private fun stringOfShape(shape: Shape): String {
        return when (shape) {
            Shape.CLOVER -> "클로버"
            Shape.DIAMOND -> "다이아몬드"
            Shape.HEART -> "하트"
            Shape.SPADE -> "스페이드"
        }
    }
}
