package blackjack.testmachine

import blackjack.model.deck.Card
import blackjack.model.deck.CardMachine
import blackjack.model.util.CardNumber
import blackjack.model.util.Pattern

class BustCardMachine : CardMachine {
    override fun shuffle(cards: List<Card>): List<Card> {
        return listOf(
            Card(CardNumber.SIXTH, Pattern.SPADE),
            Card(CardNumber.SEVENTH, Pattern.CLOVER),
            Card(CardNumber.TEN, Pattern.HEART),
        )
    }
}
