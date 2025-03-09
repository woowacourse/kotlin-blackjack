package blackjack.domain.model

class Player private constructor(override val name: String, override val cards: Cards = Cards()) : Participant() {
    constructor(name: String, vararg card: Card) : this(name, Cards(*card))
}
