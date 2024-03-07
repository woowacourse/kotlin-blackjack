package blackjack.view

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardShape
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Player

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

    fun printParticipantStatus(participant: Participant) {
        when (participant) {
            is Player -> printPlayerStatus(participant)
            is Dealer -> printDealerStatus(participant)
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("${player.name}카드 ${player.state.hand().cards.map { cardToString(it) }.joinToString(", ")}")
    }

    private fun printDealerStatus(dealer: Dealer) {
        val count = dealer.state.hand().cards.size
        println()
        if (count > 2) {
            println("${dealer.name}는 16이하라 ${count - 2}의 카드를 더 받았습니다.")
        }
    }

    fun printStatusAndScore(participants: Participants) {
        println()
        participants.getAllParticipants().forEach { participant ->
            println(
                "${participant.name}카드 ${
                    participant.state.hand().cards.map { cardToString(it) }.joinToString(", ")
                } - 결과: ${participant.state.hand().calculateSum()}",
            )
        }
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
