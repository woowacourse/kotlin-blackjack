package blackjack.testmachine

import blackjack.model.deck.Card
import blackjack.model.deck.CardMachine
import blackjack.util.CardNumber
import blackjack.util.Pattern

class BlackjackCardMachine : CardMachine {
    override fun handle(cards: List<Card>): List<Card> {
        return listOf(
            Card(CardNumber.ACE, Pattern.SPADE),
            Card(CardNumber.JACK, Pattern.CLOVER),
            Card(CardNumber.EIGHTH, Pattern.HEART),
        )
    }
}
