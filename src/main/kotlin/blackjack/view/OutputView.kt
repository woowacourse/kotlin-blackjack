package blackjack.view

import blackjack.model.domain.Card
import blackjack.model.domain.Dealer
import blackjack.model.domain.ParticipantStatus
import blackjack.model.domain.Participants
import blackjack.model.domain.Player

class OutputView {
    fun printInitCardStatus(
        dealer: Dealer,
        players: List<Participants>,
    ) {
        val playerName = players.joinToString { it.name }
        println(OUTPUT_DISTRIBUTE_CARD.format(dealer.name, playerName))
        printDealerInitCard(dealer)
        printPlayerInitCard(players)
    }

    private fun printDealerInitCard(dealer: Dealer) {
        val firstCard = listOf(dealer.cardDeck.first())
        println(PLAYER_STATUS.format(dealer.name, displayCard(firstCard)))
    }

    private fun printPlayerInitCard(players: List<Participants>) {
        players.forEach { player ->
            printCardStatus(player)
        }
        println()
    }

    fun printCardStatus(player: Participants) {
        println(makeFormat(player))
    }

    private fun makeFormat(player: Participants): String {
        return PLAYER_STATUS.format(player.name + CARD, displayCard(player.cardDeck))
    }

    private fun displayCard(cards: List<Card>): String {
        return cards.joinToString { CARD_FORMAT.format(it.cardNumber.display, it.symbol.symbol) }
    }

    fun printDealerReceiveCard(
        count: Int,
        dealer: Dealer,
    ) {
        println()
        repeat(count) {
            println(OUTPUT_DEALER_RECEIVE_CARD.format(dealer.name))
        }
        println()
    }

    fun participantsCardResult(participants: List<Participants>) {
        participants.forEach { participant ->
            println(makeFormat(participant) + OUTPUT_PARTICIPANTS_CARD_RESULT.format(participant.sumCardNumber))
        }
    }

    fun playerResult(players: List<Player>) {
        players.forEach { player ->
            println(PLAYER_STATUS.format(player.name, player.status.determineStatus()))
        }
        println()
    }

    fun dealerResult(
        dealer: Dealer,
        statusCount: Map<ParticipantStatus, Int>,
    ) {
        println(FINAL_RESULT)

        val winningCount = (statusCount[ParticipantStatus.Lose] ?: 0) + (statusCount[ParticipantStatus.Bust] ?: 0)
        val losingCount = (statusCount[ParticipantStatus.Win] ?: 0) + (statusCount[ParticipantStatus.None] ?: 0)
        val drawCount = statusCount[ParticipantStatus.Draw] ?: 0

        val resultFormat: String = OUTPUT_DEALER_RESULT.format(dealer.name, winningCount, losingCount)

        if (drawCount != 0) {
            println(resultFormat + OUTPUT_DEALER_RESULT_DRAW.format(drawCount))
        } else {
            println(resultFormat)
        }
    }

    private fun ParticipantStatus.determineStatus(): String {
        return when (this) {
            ParticipantStatus.Win, ParticipantStatus.None -> "승"
            ParticipantStatus.Draw -> "무"
            else -> "패"
        }
    }

    companion object {
        private const val OUTPUT_DISTRIBUTE_CARD: String = "\n%s와 %s에게 2장의 나누었습니다."
        private const val OUTPUT_DEALER_RECEIVE_CARD: String = "%s는 16이하라 한장의 카드를 더 받았습니다."
        private const val OUTPUT_PARTICIPANTS_CARD_RESULT: String = " - 결과: %d"
        private const val FINAL_RESULT: String = "\n## 최종 승패"
        private const val OUTPUT_DEALER_RESULT: String = "%s: %d승 %d패"
        private const val OUTPUT_DEALER_RESULT_DRAW: String = " %d무"
        private const val PLAYER_STATUS: String = "%s: %s"
        private const val CARD: String = "카드"
        private const val CARD_FORMAT: String = "%s%s"
    }
}
