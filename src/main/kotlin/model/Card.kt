package model

data class Card(private val cardRank: CardRank, private val shape: Shape) {
    val cardScore: Int = cardRank.score
    val cardName: Pair<String, String> = cardRank.name to shape.name

    fun isAceCard(): Boolean = cardRank == CardRank.ACE
}
