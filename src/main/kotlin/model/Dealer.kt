package model

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun isHit(): Boolean = cards.sum() < HIT_STANDARD_POINT

    companion object {
        private const val DEALER = "딜러"
        private const val HIT_STANDARD_POINT = 17
    }
}
