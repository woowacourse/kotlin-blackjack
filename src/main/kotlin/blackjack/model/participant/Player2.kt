package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.model.participant.testState.Finish
import blackjack.model.participant.testState.Gaming
import blackjack.model.participant.testState.HandCardState
import blackjack.model.participant.testState.InitState

class Player2(val name: String) {
    private val handCards = HandCards()

    fun initCards(cards: List<Card>) {
        require(cards.size == 2) {
            "초기 카드는 2장으로 제한됩니다."
        }
        handCards.add(cards)
    }

    private fun playTurn(
        isHit: (String) -> Boolean,
        deck: Deck,
    ) {
        var currentState: Gaming = InitState()

        do {
            val isHit = isHit(name)
            val currentState = currentState.nextTurn(handCards, isHit)
        } while (currentState is Finish)

        do {
            addCard(deck.draw(1), currentState)
            val nextState = currentState.nextTurn(handCards, isHit(name))
        } while (nextState is Finish)
    }

    private fun addCard(
        cards: List<Card>,
        currentState: HandCardState,
    ) {
        if (currentState is Finish) {
            throw IllegalStateException("더 이상 카드를 뽑을 수 없습니다.")
        } else {
            handCards.add(cards)
        }
    }
}
