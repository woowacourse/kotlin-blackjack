package model

class CardPack(private val cards: Cards) {
    val size
        get() = cards.size

    constructor(cards: List<Card>) : this(Cards(cards))

    fun pop(): Card = cards.pop()

    fun shuffled() = CardPack(cards.toList().shuffled())

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
