package blackjack

import blackjack.model.Card
import blackjack.model.CardMachine

class TestCardMachine : CardMachine {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards
    }
}
