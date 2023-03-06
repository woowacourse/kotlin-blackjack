package domain.gamer

import domain.card.Card
import domain.gamer.cards.Cards

class Dealer(override val cards: Cards) : Participant(cards) {

    constructor(vararg card: Card) : this(Cards(card.toList()))

    fun checkAvailableForPick() = calculateCardSum() <= CARD_PICK_CONDITION

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
