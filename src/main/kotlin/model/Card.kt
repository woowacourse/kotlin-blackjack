package model

data class Card(val cardRank: CardRank, val shape: Shape) {
    override fun toString(): String {
        return "${cardRank.title}${shape.title}"
    }
}
