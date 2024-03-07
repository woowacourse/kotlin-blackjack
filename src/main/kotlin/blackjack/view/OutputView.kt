package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player

object OutputView {

    fun printResult(dealer: Dealer, players: List<Player>) {
        println(buildParticipantOneCard(dealer))
        players.forEach {
            println(buildParticipantCards(it))
        }
    }

    private fun buildParticipantOneCard(participant: Participant): String {
        val name = participant.name
        val card = listOf(participant.showCard()[0])
        return buildParticipantCardsString(name, card)
    }

    private fun buildParticipantCards(participant: Participant): String {
        val name = participant.name
        val cards = participant.showCard()
        return buildParticipantCardsString(name, cards)
    }

    private fun buildParticipantCardsString(name: String, cards: List<Card>): String {
        val cardStrings = cards.map { it.cardNumber.name + it.suit.name }.joinToString(", ")
        return "${name}카드: $cardStrings"
    }
}
