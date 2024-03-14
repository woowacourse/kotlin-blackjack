package blackjack.model.card

import blackjack.model.Card

object TestCardProvider : CardProvider {
    override fun provide(): Card {
        return Card.of(Denomination.TWO, Suite.HEART)
    }
}
