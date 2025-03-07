package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier
import blackjack.domain.participant.Dealer.Companion.ACE_EXTRACT_SCORE

data class Player(
    val name: String,
) : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    override val sumOfCards get() = _cards.sumOf { card -> card.tier.values }

    override fun addCard(card: TrumpCard) {
        _cards.add(card)
    }

    override fun isBust(): Boolean = sumOfCards > BUST_STANDARD

    override fun hasAce(): Boolean = cards.map { it.tier }.contains(CardTier.ACE)

    private fun isBustByMaxAce(): Boolean = sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD

    override fun sum(): Int =
        if (hasAce() && !isBustByMaxAce()) {
            _cards.sumOf { card -> card.tier.values } + ACE_EXTRACT_SCORE
        } else {
            _cards.sumOf { card -> card.tier.values }
        }
}
