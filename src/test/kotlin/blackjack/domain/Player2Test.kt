package blackjack.domain

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Player
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Player2Test {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("peto")
    }

    @Test
    fun `플레이어가 게임을 시작하면 카드를 2장 지급받는다`() {
        val fixture = trumpCardFixture()
        fixture.forEach {
            player.addCard(it)
        }
        assertThat(player.cards2.items).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `플레이어가 에이스 카드가 없으면 에이스 카드가 없음을 반환한다`() {
        val expected = player.hasAce()

        assertEquals(expected, false)
    }

    @Test
    fun `플레이어가 에이스 카드가 있으면 에이스 카드가 있음을 반환한다`() {
        player.addCard(TrumpCard(Tier.ACE, Shape.DIA))

        val expected = player.hasAce()

        assertEquals(expected, true)
    }
}
