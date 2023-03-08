package blackjack.domain.carddeck

import blackjack.Shape
import blackjack.domain.Card
import blackjack.domain.CardNumber

object RandomCardDeck : CardDeck {

    private val Cards: List<Card> = Shape.values().fold(listOf()) { cards, shape -> cards + makeCard(shape) }

    private fun makeCard(shape: Shape): List<Card> = CardNumber.values().map { Card(shape, it) }

    override fun drawCard(): Card = Cards.shuffled().first()
}
