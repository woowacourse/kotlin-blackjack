package blackjack.testmachine

import blackjack.model.deck.Card
import blackjack.model.deck.CardMachine
import blackjack.model.util.CardNumber
import blackjack.model.util.Pattern

class NormalCardMachine : CardMachine {
    override fun handle(cards: List<Card>): List<Card> {
        return listOf(
            Card(CardNumber.TWO, Pattern.SPADE),
            Card(CardNumber.EIGHTH, Pattern.CLOVER),
            Card(CardNumber.ACE, Pattern.HEART),
            Card(CardNumber.TEN, Pattern.DIAMOND),
        )
    }
}
