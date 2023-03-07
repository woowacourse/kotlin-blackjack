package domain.gamer

import domain.card.Card
import domain.gamer.cards.Cards

class Dealer(override val cards: Cards) : Participant(cards) {

    constructor(vararg cards: Card) : this(Cards(cards.toList()))

    fun checkAvailableForPick() = calculateCardSum() <= CARD_PICK_CONDITION

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
