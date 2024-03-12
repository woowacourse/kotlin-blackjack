package blackjack.model.card

object RandomCardProvider : CardProvider {
    override fun provide(
        cardBundle: List<Card>,
        cardCount: Int,
    ): List<Card> {
        return cardBundle.shuffled().take(cardCount)
    }
}
