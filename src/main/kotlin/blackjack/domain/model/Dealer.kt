package blackjack.domain.model

class Dealer(override var hands: Hands, override val name: String = DEALER_NAME) : Participant() {
    override var record: Record = Record()

    constructor(vararg card: Card) : this(Hands(card.toList()))

    override fun showInitCards(): List<Card> = showCards(INIT_VISIBLE_CARD_COUNT)

    fun isHit(): Boolean = getScore() <= DEALER_DRAW_THRESHOLD

    companion object {
        private const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
        private const val INIT_VISIBLE_CARD_COUNT = 1
    }
}
