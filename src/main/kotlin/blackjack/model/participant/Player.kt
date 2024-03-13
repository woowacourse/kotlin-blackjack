package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.HandCards
import blackjack.model.participant.state.Blackjack
import blackjack.model.participant.state.Bust
import blackjack.model.participant.state.Normal
import blackjack.model.participant.state.ParticipantState

class Player(val name: String) : GameParticipant(HandCards()) {
    override fun add(cards: List<Card>): Boolean =
        if (!isBust()) {
            handCards.add(cards)
            true
        } else {
            false
        }

    fun getState(): ParticipantState {
        return if (handCards.isBlackjackCard()) {
            Blackjack()
        } else if (isBust()) {
            Bust()
        } else {
            Normal(getScore())
        }
    }
}
