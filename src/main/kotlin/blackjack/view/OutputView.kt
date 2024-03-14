package blackjack.view

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardShape
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.WinningState

object OutputView {
    fun printParticipantsStatus(participants: Participants) {
        val dealer = participants.getDealer()
        val players = participants.getPlayers()
        println()
        println(
            "${dealer.getName()}와 ${
                players.map { it.getName() }.joinToString(", ")
            }에게 2장을 나눠줬습니다.",
        )
        println("${dealer.getName()} : ${cardToString(dealer.getCards().first())}")
        players.forEach { println("${it.getName()}카드: ${it.getCards().joinToString(", ") { cardToString(it) }}") }
        println()
    }

    fun printPlayerStatus(player: Player) {
        println("${player.getName()}카드 ${player.getCards().joinToString(", ") { cardToString(it) }}")
    }

    fun printDealerStatus(dealer: Dealer) {
        val count = dealer.getCards().size
        println()
        if (count > 2) {
            println("${dealer.getName()}는 16이하라 ${count - 2}의 카드를 더 받았습니다.")
        }
    }

    fun printParticipantsStatusAndScore(participants: Participants) {
        println()
        participants.getAllParticipants().forEach { participant ->
            println(
                "${participant.getName()}카드 ${
                    participant.getCards().joinToString(", ") { cardToString(it) }
                } - 결과: ${participant.getCardsSum()}",
            )
        }
    }

    fun printGameResult(result: Map<Participant, WinningState>) {
        val playersCount = result.size - 1
        println("\n## 최종 승패")
        result.entries.forEach { (participant, winningState) ->
            when (participant) {
                is Dealer -> printDealerResult(participant, winningState, playersCount)
                is Player -> printPlayerResult(participant, winningState)
            }
        }
    }

    fun printFinalProfits(profits: Map<Participant, Double>) {
        println("\n## 최종 수익")

        profits.entries.forEach { (participant, profit) ->
            println("${participant.getName()}: ${profit.toInt()} ")
        }
    }

    private fun printDealerResult(
        dealer: Dealer,
        winningState: WinningState,
        playersCount: Int,
    ) {
        val drawCount = playersCount - (winningState.wins + winningState.losses)
        println("${dealer.getName()}: ${winningState.wins}승 ${winningState.losses}패 ${drawCount}무")
    }

    private fun printPlayerResult(
        participant: Participant,
        winningState: WinningState,
    ) {
        val resultMessage =
            when {
                winningState.wins == 1 -> "승"
                winningState.losses == 1 -> "패"
                else -> "무"
            }
        println("${participant.getName()}: $resultMessage")
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
