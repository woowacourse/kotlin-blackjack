package model.participants

import model.card.Card
import model.card.Deck

class Dealer(
    override val hand: Hand,
    override val participantName: ParticipantName =
        ParticipantName.fromInput(
            "딜러",
        ),
) : Participant(hand, participantName) {
    fun play(deck: Deck): Int {
        var hitCount = 0

        while (canHit()) {
            hit(deck.pop())
            hitCount++
        }

        return hitCount
    }

    override fun hit(card: Card): Boolean {
        if (canHit()) {
            hand.draw(card)
            return canHit()
        }
        return false
    }

    override fun canHit(): Boolean = getPointIncludingAce().amount <= HIT_THRESHOLD

    companion object {
        private const val HIT_THRESHOLD = 16
    }
}
