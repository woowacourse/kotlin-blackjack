package blackjack.model.card

import blackjack.model.Card

object TestCardProvider : CardProvider {
    override fun provide(cardBundle: List<Card>): Card {
        return Card.of(Denomination.TWO, Suite.HEART)
    }
}
