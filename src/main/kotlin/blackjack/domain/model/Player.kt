package blackjack.domain.model

class Player(override var hands: Hands, override val name: String) : Participant() {
    constructor(name: String, vararg card: Card) : this(Hands(card.toList()), name)

    override fun showInitCards() = showCards(INIT_VISIBLE_CARD_COUNT)

    companion object {
        private const val INIT_VISIBLE_CARD_COUNT = 2
    }
}
