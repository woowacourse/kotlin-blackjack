package blackjack.model.card

interface CardProvider {
    fun provide(cardBundle: List<Card>): Card
}