package blackjack.model

class ScoreBoard {
    val handCard = HandCard()
    var cardSum = 0
        private set

    fun add(card: Card) {
        handCard.add(card)
        cardSum += card.getValue()
    }
}
