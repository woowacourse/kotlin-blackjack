package blackjack.domain.model

class Player(override val name: String, override var hands: Hands) : Participant() {
    constructor(name: String, vararg card: Card) : this(name, Hands(card.toList()))
}
