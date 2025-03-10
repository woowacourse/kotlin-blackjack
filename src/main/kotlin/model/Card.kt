package model

data class Card(val cardRank: CardRank, val shape: Shape) {
    companion object {
        private val cache = mutableMapOf<Pair<CardRank, Shape>, Card>()

        fun of(
            cardRank: CardRank,
            shape: Shape,
        ): Card {
            return cache.getOrPut(Pair(cardRank, shape)) { Card(cardRank, shape) }
        }
    }
}
