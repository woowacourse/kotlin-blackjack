package blackjack.view

import blackjack.model.domain.Card
import blackjack.model.domain.Participants

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

    companion object {
        private const val OUTPUT_DISTRIBUTE_CARD: String = "%s와 %s에게 2장의 나누었습니다."
        private const val OUTPUT_DEALER_RECEIVE_CARD: String = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val OUTPUT_PARTICIPANTS_CARD_RESULT = " - 결과: %d"
        private const val PLAYER_STATUS: String = "%s: %s"
        private const val CARD: String = "카드"
        private const val CARD_FORMAT: String = "%s%s"
        private const val SEPARATOR: String = ", "
    }
}
