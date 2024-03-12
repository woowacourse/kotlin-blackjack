package blackjack.model.deck

class HandCards(private val deck: Deck) {
    private val _cards: MutableList<Card> = mutableListOf()
    private val cards: List<Card>
        get() = _cards.toList()

    init {
        _cards.addAll(deck.draw(INIT_CARD_AMOUNT))
    }

    fun add() {
        _cards.addAll(deck.draw(HIT_CARD_AMOUNT))
    }

    fun getFirstCard(): String = "${cards.first().cardNumber.value}${cards.first().pattern.shape}"

    fun getAllCards(): String = cards.joinToString(SPLIT_DELIMITER) { "${it.cardNumber.value}${it.pattern.shape}" }

    fun getCardsScore(): Int = cards.sumOf { it.cardNumber.score }

    fun hasAce(): Boolean = cards.any { it.cardNumber == CardNumber.ACE }

    fun getCardsSize(): Int = cards.size

    companion object {
        const val SPLIT_DELIMITER = ", "
        private const val INIT_CARD_AMOUNT = 2
        private const val HIT_CARD_AMOUNT = 1
    }
}
