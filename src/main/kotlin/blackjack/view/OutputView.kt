package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardBunch
import blackjack.domain.CardNumber
import blackjack.domain.Participants
import blackjack.domain.Player

object OutputView {

    private const val DEALER_INITIAL_CARD_SCRIPT = "딜러: %s"
    private const val DISTRIBUTE_SCRIPT = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val CAN_GET_CARD_SCRIPT = "딜러는 16이하라 한 장의 카드를 더 받았습니다."
    private const val CANNOT_GET_CARD_SCRIPT = "딜러는 17이상이라 카드를 더 받지 못합니다."

    private fun makeCardString(card: Card): String = getCardNumberString(card.cardNumber) + card.shape.korean

    private fun makeBunchString(bunch: CardBunch): String =
        bunch.cards.joinToString(separator = ", ") { makeCardString(it) }

    fun printDealerInitialCard(cardBunch: CardBunch) {
        println(DEALER_INITIAL_CARD_SCRIPT.format(makeCardString(cardBunch.cards.first())))
    }

    fun printPlayerCards(player: Player) {
        val bunchString = makeBunchString(player.state.hand)
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
        println("딜러 카드 : ${makeBunchString(participants.dealer.state.hand)} - 결과: ${participants.dealer.state.hand.getTotalScore()}")
        participants.players.value.forEach { player ->
            val bunchString = makeBunchString(player.state.hand)
            println("${player.name}카드 : $bunchString - 결과: ${player.state.hand.getTotalScore()}")
        }
        println()
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
