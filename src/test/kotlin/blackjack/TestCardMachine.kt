package blackjack

import blackjack.model.Card
import blackjack.model.CardMachine
import blackjack.model.CardNumber
import blackjack.model.Pattern

class TestCardMachine : CardMachine {
    override fun shuffle(cards: List<Card>): List<Card> {
        return listOf(
            Card(CardNumber.TWO, Pattern.SPADE),
            Card(CardNumber.EIGHTH, Pattern.CLOVER),
            Card(CardNumber.ACE, Pattern.HEART),
        )
    }
}
