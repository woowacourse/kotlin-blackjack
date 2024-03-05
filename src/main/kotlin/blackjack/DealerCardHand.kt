package blackjack

class DealerCardHand(hand: List<Card>) : CardHand(hand) {
    constructor(vararg card: Card) : this(card.toList())

    override fun getCardHandState(isHit: Boolean): CardHandState {
        val sum = sum()

        return when {
            sum > 21 -> CardHandState.BURST
            sum == 21 -> CardHandState.BLACKJACK
            sum > 16 -> CardHandState.STAY
            else -> CardHandState.HIT
        }
    }
}
