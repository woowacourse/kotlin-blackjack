package model.card

data class Card(private val cardRank: CardRank, private val shape: Shape) {
    val cardScore: Int = cardRank.score
    val cardName: CardName = CardName(cardRank.name, shape.name)
    val isAceCard: Boolean = cardRank == CardRank.ACE
}
