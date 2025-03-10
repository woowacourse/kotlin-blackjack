package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

abstract class Participant(
    val cards: ParticipantCards,
) {
    fun receiveCard(card: TrumpCard) {
        cards.add(card)
    }

    fun getAllCards(): List<TrumpCard> = cards.allCards

    fun isBust(): Boolean = cards.sumOfCards > BUST_STANDARD

    fun isBustByMaxAce(): Boolean = cards.sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD

    fun finalScore(): Int =
        if (cards.hasAce() && !isBustByMaxAce()) {
            cards.sumOfCards + ACE_EXTRACT_SCORE
        } else {
            cards.sumOfCards
        }

    companion object {
        const val ACE_EXTRACT_SCORE = 10
    }
}
