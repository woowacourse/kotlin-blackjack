package blackjack.model.card

interface CardDrawStrategy {
    fun drawCard(): Card

    fun resetCardDeck()
}
