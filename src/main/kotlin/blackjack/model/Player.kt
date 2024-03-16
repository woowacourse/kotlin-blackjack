package blackjack.model

class Player(name: String) : Participant(name, PlayerStrengthPolicy()) {
    override fun initialCardsList(): List<Card> {
        return cards.toList().take(2)
    }
    override fun isHitable(): Boolean = !isBusted()
}
