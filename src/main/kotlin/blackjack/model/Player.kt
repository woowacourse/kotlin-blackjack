package blackjack.model

class Player(name: String, val stake: Double) : Participant(name, PlayerStrengthPolicy()) {
    constructor(name: String) : this(name, 0.0)

    override fun initialCardsList(): List<Card> {
        return cards.toList().take(2)
    }

    override fun isHitable(): Boolean = !isBusted()
}
