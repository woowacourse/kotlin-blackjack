package blackjack.model.card

object TestCardProvider : CardProvider {
    override fun provide(): Card {
        return Card.of(Denomination.TWO, Suite.HEART)
    }
}
