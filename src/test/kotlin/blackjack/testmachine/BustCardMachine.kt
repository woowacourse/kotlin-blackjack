package blackjack.testmachine

import blackjack.model.deck.Card
import blackjack.model.deck.CardMachine
import blackjack.util.CardNumber
import blackjack.util.Shape

class BustCardMachine : CardMachine {
    override fun handle(cards: List<Card>): List<Card> {
        return listOf(
            Card(CardNumber.SIXTH, Shape.SPADE),
            Card(CardNumber.SEVENTH, Shape.CLOVER),
            Card(CardNumber.TEN, Shape.HEART),
        )
    }
}
