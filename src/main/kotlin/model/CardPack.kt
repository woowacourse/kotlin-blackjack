package model

class CardPack(private val cards: Cards) {
    val size
        get() = cards.cards.size
    constructor(cards: List<Card>) : this(Cards(cards))

    fun pop(): Card {
        require(cards.cards.isNotEmpty()) { OUT_OF_INDEX_CARDS_CURSOR }
        return cards.cards.pop()
    }

    fun shuffled() = CardPack(Cards(cards.cards.shuffled()))

    companion object {
        private const val OUT_OF_INDEX_CARDS_CURSOR = "카드를 모두 사용했습니다."
    }
}
