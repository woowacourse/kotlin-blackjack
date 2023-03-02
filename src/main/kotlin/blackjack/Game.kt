package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participant
import blackjack.domain.Participant.Companion.INIT_CARD_SIZE
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = InputView.getNames().map(::Player)
    val dealer = Dealer()
    dealCards(players + dealer)
    ResultView.printSetUp(dealer, players)
}

private fun dealCards(participants: List<Participant>) {
    repeat(INIT_CARD_SIZE) {
        participants.forEach { it.receive(Deck.draw()) }
    }
}
