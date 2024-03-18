package blackjack.model.participant

import blackjack.model.BettingAmount
import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.state.State

class Player(val name: String, deck: Deck) : GameParticipant(HandCards(), deck) {
    var state: State = State.Start(this)
    lateinit var bettingAmount: BettingAmount

    init {
        state = State.Start(this).decisionState()
    }
}
