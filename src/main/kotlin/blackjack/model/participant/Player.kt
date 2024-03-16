package blackjack.model.participant

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.state.Start
import blackjack.state.State

class Player(val name: String, deck: Deck) : GameParticipant(HandCards(deck)) {
    var state: State = Start(this)

    init {
        state = Start(this).drawCard()
    }
}
