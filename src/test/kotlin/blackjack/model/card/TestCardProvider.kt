package blackjack.model.card

object TestCardProvider : CardProvider {
    override fun provide(cardBundle: List<Card>): Card {
        return Card.of("2", "하트")
    }
}
