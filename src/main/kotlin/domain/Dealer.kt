package domain

class Dealer(cards: Cards) : Participant(Name("딜러"), cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(1)
    }

    override fun isPossibleDrawCard(): Boolean {
        if (cards.maxSumState().sum <= 16) return true
        return false
    }
}
