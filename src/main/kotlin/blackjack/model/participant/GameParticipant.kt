package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.HandCards

abstract class GameParticipant(protected val handCards: HandCards) {
    fun initCards(cards: List<Card>) {
        require(cards.size == INIT_CARD_SIZE) {
            "초기 카드는 2장으로 제한됩니다."
        }
        handCards.add(cards)
    }

    abstract fun add(cards: List<Card>): Boolean

    fun isBust(): Boolean = handCards.calculateCardScore() > BLACKJACK_NUMBER

    fun isBlackjack(): Boolean = handCards.isBlackjackCard()

    fun getAllCards() = handCards.cards

    fun getScore() = handCards.calculateCardScore()

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val INIT_CARD_SIZE = 2
    }
}
