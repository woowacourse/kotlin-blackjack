package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participant
import blackjack.domain.Participant.Companion.INIT_CARD_SIZE
import blackjack.domain.Player
import blackjack.view.InputView

fun main() {
    val playerNames = InputView.getNames()
    val players = playerNames.map(::Player)
    val dealer = Dealer()
    dealCards(players + dealer)
}

private fun dealCards(participants: List<Participant>) {
    repeat(INIT_CARD_SIZE) {
        participants.forEach { it.receive(Deck.draw()) }
    }
}
