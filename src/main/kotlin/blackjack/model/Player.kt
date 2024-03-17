package blackjack.model

class Player(name: String, val stake: Money) : Participant(name, PlayerStrengthPolicy()) {
    constructor(name: String) : this(name, Money(1.0))

    init {
        require(stake > Money(0.0)) { "판돈은 0원 이상이어야 합니다" }
    }

    override fun initialCardsList(): List<Card> {
        return cards.toList().take(2)
    }

    override fun isHitable(): Boolean = !isBusted()
}
