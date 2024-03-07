package blackjack.model

interface CardProvider {
    fun provide(cardBundle: List<Card>): Card
}
