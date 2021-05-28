package domain.card

data class Cards(val cards: List<Card> = listOf()) {

    val score = Score(cards)

    fun add(card: Card): Cards {
        return Cards(cards.plus(card))
    }
}