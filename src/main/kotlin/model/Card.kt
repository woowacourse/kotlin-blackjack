package model

data class Card(private val cardRank: CardRank, private val shape: Shape) {
    val cardScore: Int
        get() = cardRank.score

    val cardName: String
        get() = cardRank.title + shape.title

    fun isAceCard(): Boolean = cardName.contains("A")
}
