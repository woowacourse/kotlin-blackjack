package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.HandCards

abstract class GameParticipant() {
    protected val handCards = HandCards()

    fun initCards(cards: List<Card>) {
        require(cards.size == INIT_CARD_AMOUNT) {
            "초기 카드는 2장으로 제한됩니다."
        }
        handCards.initCard(cards)
    }

    fun getAllCards() = handCards.cards

    fun getScore() = handCards.calculateScore()

    companion object {
        const val INIT_CARD_AMOUNT = 2
    }
}
