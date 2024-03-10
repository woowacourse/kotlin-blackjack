package blackjack.testmachine

import blackjack.model.deck.Card
import blackjack.model.deck.CardMachine
import blackjack.util.CardNumber
import blackjack.util.Shape

class NormalCardMachine : CardMachine {
    override fun handle(cards: List<Card>): List<Card> {
        return listOf(
            Card(CardNumber.TWO, Shape.SPADE),
            Card(CardNumber.EIGHTH, Shape.CLOVER),
            Card(CardNumber.ACE, Shape.HEART),
            Card(CardNumber.TEN, Shape.DIAMOND),
        )
    }
}
