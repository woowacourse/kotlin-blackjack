package model

class Card(private val cardRank: CardRank, private val shape: Shape) {
    val cardScore: Int
        get() = cardRank.score

    val cardName: String
        get() = cardRank.title + shape.title

    fun isAceCard(): Boolean = cardRank == CardRank.ACE

    companion object {
        private val cache = mutableMapOf<Pair<CardRank, Shape>,Card>()

        fun of(
            cardRank: CardRank,
            shape: Shape,
        ): Card {
            return cache.getOrPut(Pair(cardRank, shape)) { Card(cardRank, shape) }
        }
    }
}
