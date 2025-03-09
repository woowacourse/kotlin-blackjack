package blackjack.domain.model

class Player private constructor(override val name: String, override val hands: Hands = Hands()) : Participant() {
    constructor(name: String, vararg card: Card) : this(name, Hands(*card))
}
