package blackjack.view

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardShape
import blackjack.model.Dealer
import blackjack.model.Dealer.Companion.DEALER_NAME
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.WinningResult

object OutputView {
    fun printInitialStatus(participants: Participants) {
        val dealer = participants.dealer
        println(
            "\n${participants.dealer.name}와 ${
                participants.players.map { it.name }.joinToString(", ")
            }에게 2장을 나눠줬습니다.",
        )
        println("${dealer.name} : ${cardToString(dealer.getCards()[0])}")
        participants.players.forEach {
            println("${it.name}카드: ${it.getCards().joinToString(", ") { cardToString(it) }}")
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
        println("${player.name}카드 : ${player.getCards().joinToString(", ") { cardToString(it) }}\n")
    }

    private fun printDealerStatus(dealer: Dealer) {
        println("${dealer.name}는 16이하라 ${dealer.countAdditionalDrawnCards()}장의 카드를 더 받았습니다.\n")
    }

    fun printStatusAndScore(participants: Participants) {
        participants.getAllParticipants().forEach { participant ->
            println(
                "${participant.name}카드 : ${
                    participant.getCards().joinToString(", ") { cardToString(it) }
                } - 결과: ${participant.getSumOfCards()}",
            )
        }
    }

    fun printResult(
        dealerResult: Map<WinningResult, Int>,
        playerResult: Map<Player, WinningResult>,
    ) {
        println("\n## 최종 승패")
        printDealerResult(dealerResult)
        printPlayerResult(playerResult)
    }

    private fun printDealerResult(dealerResult: Map<WinningResult, Int>) {
        println(
            "${DEALER_NAME}: " +
                "${dealerResult.getOrDefault(WinningResult.WIN, 0)}승 " +
                "${dealerResult.getOrDefault(WinningResult.LOSE, 0)}패 " +
                "${dealerResult.getOrDefault(WinningResult.DRAW, 0)}무",
        )
    }

    private fun printPlayerResult(playerResult: Map<Player, WinningResult>) {
        playerResult.entries.forEach { (player, result) ->
            println("${player.name} : ${getWinningResultString(result)}")
        }
    }

    private fun getWinningResultString(result: WinningResult): String =
        when (result) {
            WinningResult.WIN -> "승"
            WinningResult.LOSE -> "패"
            WinningResult.DRAW -> "무"
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
