package model

data class Hand(private val cards: Cards) {
    val size
        get() = cards.size

    constructor(cards: List<Card>) : this(Cards(cards))

    fun toList() = cards.toList()

    fun add(card: Card) {
        cards.add(card)
    }

    fun sum(): Int = cards.sum()

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
