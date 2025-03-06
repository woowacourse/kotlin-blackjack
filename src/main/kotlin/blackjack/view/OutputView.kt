package blackjack.view

import blackjack.model.domain.Card
import blackjack.model.domain.Dealer
import blackjack.model.domain.Participants
import blackjack.model.domain.Player

class OutputView {
    fun printInitCardStatus(
        dealer: Participants,
        players: List<Participants>,
    ) {
        val playerName = players.joinToString(SEPARATOR) { it.name }
        println(OUTPUT_DISTRIBUTE_CARD.format(dealer.name, playerName))
        printDealerInitCard(dealer)
        printPlayerInitCard(players)
    }

    private fun printDealerInitCard(player: Participants) {
        val firstCard = listOf(player.cardDeck.first())
        println(PLAYER_STATUS.format(player.name, displayCard(firstCard)))
    }

    private fun printPlayerInitCard(players: List<Participants>) {
        players.forEach { player ->
            printCardStatus(player)
        }
    }

    fun printCardStatus(player: Participants) {
        println(makeFormat(player))
    }

    private fun displayCard(cards: List<Card>): String {
        return cards.joinToString(SEPARATOR) { CARD_FORMAT.format(it.cardNumber.display, it.symbol.symbol) }
    }

    private fun makeFormat(player: Participants): String {
        return PLAYER_STATUS.format(player.name + CARD, displayCard(player.cardDeck))
    }

    fun printDealerReceiveCard(count: Int) {
        repeat(count) {
            println(OUTPUT_DEALER_RECEIVE_CARD)
        }
    }

    fun participantsCardResult(participants: List<Participants>) {
        participants.forEach { participant ->
            println(makeFormat(participant) + OUTPUT_PARTICIPANTS_CARD_RESULT.format(participant.sumCardNumber))
        }
    }

    fun gameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(FINAL_RESULT)
        dealerResult(dealer, players)
        playerResult(players)
    }

    private fun playerResult(players: List<Player>) {
        players.forEach { player ->
            println(PLAYER_STATUS.format(player.name, determineStatus(player.alive)))
        }
    }

    private fun determineStatus(status: Boolean): String {
        if (status) {
            return "승"
        }
        return "패"
    }

    private fun dealerResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val winningCount = players.filter { it.alive == true }.count()
        val losingCount = players.size - winningCount

        println(OUTPUT_DEALER_RESULT.format(dealer.name, winningCount, losingCount))
    }

    companion object {
        private const val OUTPUT_DISTRIBUTE_CARD: String = "%s와 %s에게 2장의 나누었습니다."
        private const val OUTPUT_DEALER_RECEIVE_CARD: String = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val OUTPUT_PARTICIPANTS_CARD_RESULT = " - 결과: %d"
        private const val FINAL_RESULT = "## 최종 승패"
        private const val OUTPUT_DEALER_RESULT = "%s: %d승 %d패"
        private const val PLAYER_STATUS: String = "%s: %s"
        private const val CARD: String = "카드"
        private const val CARD_FORMAT: String = "%s%s"
        private const val SEPARATOR: String = ", "
    }
}
