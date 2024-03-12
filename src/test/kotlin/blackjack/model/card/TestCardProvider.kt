package blackjack.model.card

object TestCardProvider : CardProvider {
    override fun provide(
        cardBundle: List<Card>,
        cardCount: Int,
    ): List<Card> {
        return List(cardCount) { Card.of("K", "하트") }
    }
}
