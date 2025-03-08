package model

data class Card(private val cardRank: CardRank, private val shape: Shape) {
    val cardScore: Int = cardRank.score
    val cardName: String
        get() = cardRank.title + shape.title

    fun isAceCard(): Boolean = cardRank == CardRank.ACE
}
