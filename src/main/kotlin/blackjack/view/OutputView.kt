package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player

object OutputView {

    fun printInitialResult(dealer: Dealer, players: List<Player>) {
        println(buildDealerInitialCard(dealer))
        players.forEach {
            printParticipantStatus(it)
        }
    }

    fun printParticipantStatus(participant: Participant) {
        println(buildParticipantCards(participant))
    }

    private fun buildDealerInitialCard(dealer: Dealer): String {
        val name = dealer.name
        val card = listOf(dealer.showCard()[0])
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

    fun printBlackJackMessage(participant: Participant) {
        println("${participant.name}는 블랙잭 입니다.")
    }

    fun printBustedMessage(participant: Participant) {
        println("${participant.name}는 버스트 됐습니다.")
    }
}
