package domain.participant

import domain.card.Deck
import domain.ResponseType

open class Participants(val participants: List<Participant>) : List<Participant> by participants {

    fun drawCard(deck: Deck) {
        participants.forEach { it.draw(deck.pop(), ResponseType.HIT) }
    }
}
