package blackjack.domain

import blackjack.domain.card.PlayerCards
import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerCardsTest {
    private lateinit var cards: PlayerCards

    @BeforeEach
    fun setUp() {
        cards = PlayerCards(emptySet())
    }

    @Test
    fun `카드를 추가하면 보유한 카드가 1장 늘어난다`() {
        val newCard = cards.add(TrumpCard(Tier.TEN, Shape.DIA))

        assertEquals(newCard.items.size, 1)
    }

    @Test
    fun `에이스 카드가 없으면 에이스 카드가 없음을 반환한다`() {
        val expected = cards.hasAce()
        assertEquals(expected, false)
    }

    @Test
    fun `에이스 카드가 있으면 에이스 카드가 있음을 반환한다`() {
        val newCard = cards.add(TrumpCard(Tier.ACE, Shape.DIA))
        val expected = newCard.hasAce()

        assertEquals(expected, true)
    }
}
