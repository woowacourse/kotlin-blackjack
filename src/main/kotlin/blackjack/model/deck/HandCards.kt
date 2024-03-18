package blackjack.model.deck

class HandCards() {
    private val _cards: MutableList<Card> = mutableListOf()
    private val cards: List<Card>
        get() = _cards.toList()

    fun add(
        deck: Deck,
        cardAmount: Int,
    ) {
        _cards.addAll(deck.draw(cardAmount))
    }

    fun getFirstCard(): String = cards.first().toString()

    fun getAllCards(): String = cards.joinToString(SPLIT_DELIMITER) { it.toString() }

    fun getCardsScore(): Int = cards.sumOf { it.cardNumber.score }

    fun hasAce(): Boolean = cards.any { it.cardNumber == CardNumber.ACE }

    fun getCardsSize(): Int = cards.size

    companion object {
        const val SPLIT_DELIMITER = ", "
    }
}
