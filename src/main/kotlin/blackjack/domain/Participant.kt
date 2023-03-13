package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

interface Participant {
    val cardBunch: CardBunch

    fun receiveCard(card: Card) {
        cardBunch.addCard(card)
    }

    fun canHit(): Boolean

    fun getScore(): Int

    fun isBlackjack(): Boolean = cardBunch.size() == 2 && getScore() == MAX_SCORE_CONDITION
}
