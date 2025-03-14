package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.card.CardTier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Participant.Companion.ACE_HARD_SCORE
import blackjack.domain.participant.Participant.Companion.ACE_SOFT_SCORE
import blackjack.domain.participant.Participant.Companion.BLACKJACK_SCORE
import blackjack.domain.participant.Participant.Companion.INITIAL_CARD_COUNT

class ParticipantCards(
    private val _cards: MutableList<TrumpCard> = mutableListOf(),
) {
    val allCards: List<TrumpCard> get() = _cards.toList()
    val sumOfCards: Int get() = _cards.sumOf { it.tier.values }
    private val size: Int get() = _cards.size

    fun add(card: TrumpCard) {
        _cards.add(card)
    }

    fun hasAce(): Boolean = _cards.any { it.tier == CardTier.ACE }

    fun isBust(extraScore: Int = ACE_HARD_SCORE): Boolean = sumOfCards + extraScore > BUST_STANDARD

    fun finalScore(): Int =
        if (hasAce() && !isBust(ACE_SOFT_SCORE)) {
            sumOfCards + ACE_SOFT_SCORE
        } else {
            sumOfCards
        }

    fun isBlackJack(): Boolean = size == INITIAL_CARD_COUNT && finalScore() == BLACKJACK_SCORE
}
