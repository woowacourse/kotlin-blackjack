package blackjack.domain

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Participant
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `cards 프로퍼티가 방어적 복사를 수행하는지 테스트`() {
        val participant =
            object : Participant() {
                override fun getInitialCards(): Set<TrumpCard> = emptySet()

                override fun isDrawable(): Boolean = true
            }

        val originalCards = participant.cards
        val newCard = TrumpCard(Tier.ACE, Shape.DIA)

        participant.addCard(newCard)

        assertNotSame(originalCards, participant.cards)
        assertFalse(originalCards.items.contains(newCard))
        assertTrue(participant.cards.items.contains(newCard))
    }
}
