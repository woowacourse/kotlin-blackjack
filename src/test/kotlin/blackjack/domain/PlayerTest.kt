package blackjack.domain

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Player
import blackjack.fixture.bustTrumpCardFixture
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
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
        assertThat(player.cards.items).containsExactly(*fixture.toTypedArray())
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

    @Test
    fun `플레이어 카드의 총합이 21을 초과하면 버스트된다`() {
        bustTrumpCardFixture().forEach {
            player.addCard(it)
        }
        assertEquals(player.isBust(), true)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되지 않았으면 카드 총합에 10을 더한다`() {
        player.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        player.addCard(TrumpCard(Tier.NINE, Shape.HEART))

        assertEquals(player.totalScore(), 20)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되었으면 카드 총합을 유지한다`() {
        player.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        player.addCard(TrumpCard(Tier.SEVEN, Shape.HEART))
        player.addCard(TrumpCard(Tier.NINE, Shape.HEART))

        assertEquals(player.totalScore(), 17)
    }
}
