package blackjack.view

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardShape
import blackjack.model.Participants

object OutputView {
    fun printInitialStatus(participants: Participants) {
        println()
        println(
            "${participants.dealer.name}와 ${
                participants.players.map { it.name }.joinToString(", ")
            }에게 2장을 나눠줬습니다.",
        )
        println("${participants.dealer.name} : ${cardToString(participants.dealer.state.hand().cards[0])}")
        participants.players.forEach {
            println("${it.name}카드: ${it.state.hand().cards.map { cardToString(it) }.joinToString(", ")}")
        }
        println()
    }

    private fun cardToString(card: Card): String {
        val cardNumber =
            when (card.number) {
                CardNumber.JACK -> "J"
                CardNumber.QUEEN -> "Q"
                CardNumber.KING -> "K"
                CardNumber.ACE -> "A"
                else -> card.number.value.toString()
            }

        val cardShape =
            when (card.shape) {
                CardShape.DIAMOND -> "다이아몬드"
                CardShape.HEART -> "하트"
                CardShape.SPADE -> "스페이드"
                CardShape.CLOVER -> "클로버"
            }
        return cardNumber + cardShape
    }
}
