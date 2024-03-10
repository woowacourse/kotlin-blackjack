package blackjack.testmachine

import blackjack.model.deck.Card
import blackjack.model.deck.CardMachine
import blackjack.util.CardNumber
import blackjack.util.Shape

class BlackjackCardMachine : CardMachine {
    override fun handle(cards: List<Card>): List<Card> {
        return listOf(
            Card(CardNumber.ACE, Shape.SPADE),
            Card(CardNumber.JACK, Shape.CLOVER),
            Card(CardNumber.EIGHTH, Shape.HEART),
        )
    }
}
