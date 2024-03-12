package model.participants

import model.card.Card
import model.card.Deck
import model.result.Point.Companion.compareTo

class Player(
    override val hand: Hand,
    override val participantName: ParticipantName =
        ParticipantName.fromInput(
            "Player",
        ),
) : Participant(hand, participantName) {
    override fun hit(card: Card): Boolean {
        if (canHit()) {
            hand.draw(card)
            return canHit()
        }
        return false
    }

    override fun canHit(): Boolean = getPointIncludingAce() < BUST_BOUND
}
