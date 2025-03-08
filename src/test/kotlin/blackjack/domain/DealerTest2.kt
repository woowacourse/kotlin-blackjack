package blackjack.domain

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest2 {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러가 게임을 시작하면 카드를 2장 지급받는다`() {
        val fixture = trumpCardFixture()
        fixture.forEach {
            dealer.addCard(it)
        }
        assertThat(dealer.cards2.items).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `딜러가 에이스 카드가 없으면 에이스 카드가 없음을 반환한다`() {
        val expected = dealer.hasAce()

        assertEquals(expected, false)
    }

    @Test
    fun `딜러가 에이스 카드가 있으면 에이스 카드가 있음을 반환한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))

        val expected = dealer.hasAce()

        assertEquals(expected, true)
    }
}
