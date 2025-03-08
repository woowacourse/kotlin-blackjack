package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.card.PlayerCards
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer.Companion.ACE_EXTRACT_SCORE

data class Player(
    val name: String,
) : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    private var _cards2 = PlayerCards(emptySet())
    val cards2 get() = _cards2

    override val sumOfCards get() = cards2.sumOfCards

    override fun addCard(card: TrumpCard) {
        _cards2 = cards2.add(card)
    }

    // 추상화
    override fun isBust(): Boolean = sumOfCards > BUST_STANDARD

    override fun hasAce(): Boolean = _cards2.hasAce()

    private fun isBustByMaxAce(): Boolean = sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD

    override fun sum(): Int =
        if (hasAce() && !isBustByMaxAce()) {
            _cards.sumOf { card -> card.tier.values } + ACE_EXTRACT_SCORE
        } else {
            _cards.sumOf { card -> card.tier.values }
        }
}
