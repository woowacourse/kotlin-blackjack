package blackjack.model.participant

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards

class Player(val name: String, private val deck: Deck) : GameParticipant(HandCards()) {
    init {
        handCards.create(deck)
    }

    fun addCard(playerInput: Boolean): Boolean =
        if (isCanAddCard(playerInput)) {
            handCards.add(deck)
            true
        } else {
            false
        }

    private fun isCanAddCard(isAdd: Boolean) = isAdd && !isBust()
}
