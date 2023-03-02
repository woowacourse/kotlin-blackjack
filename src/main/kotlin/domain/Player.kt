package domain

class Player(name: Name, cards: Cards) : Participant(name, cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(2)
    }

    override fun isMoreAddCard(): Boolean {
        return when (cards.minSumState()) {
            is Cards.State.Burst -> false
            is Cards.State.NoBurst -> true
        }
    }

}
