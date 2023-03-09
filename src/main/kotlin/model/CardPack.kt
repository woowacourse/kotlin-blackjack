package model

class CardPack(private val cards: Cards) {
    val size
        get() = cards.size

    constructor(cards: List<Card>) : this(Cards(cards))

    fun pop(): Card = cards.pop()

    fun shuffled() = CardPack(cards.toList().shuffled())
}
