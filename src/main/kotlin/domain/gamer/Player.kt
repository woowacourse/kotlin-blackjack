package domain.gamer

import domain.card.Card
import domain.gamer.cards.Cards

class Player(val name: String, override val cards: Cards) : Participant(cards) {

    constructor(name: String, vararg cards: Card) : this(name, Cards(cards.toList()))
}
