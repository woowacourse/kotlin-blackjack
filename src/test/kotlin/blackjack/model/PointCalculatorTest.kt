package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PointCalculatorTest {
    private lateinit var pointCalculator: PointCalculator

    @BeforeEach
    fun setUp() {
        pointCalculator = DefaultPointCalculator()
    }

    @Test
    fun `에이스가 없을 때 - 카드의 합이 21에 가장 가까운 수 반환`() {
        // given
        val cards = listOf(createCard(rank = Rank.SIX), createCard(rank = Rank.FIVE))
        val expected = 11
        // when
        val actual = pointCalculator.sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 없을 때 - 카드의 합이 21보다 크면 null`() {
        // given
        val cards = List(3) { createCard(rank = Rank.QUEEN) }
        // when
        val actual = pointCalculator.sumOrNull(cards)
        // then
        assertThat(actual).isNull()
    }

    @Test
    fun `에이스가 있을 때 - 카드의 합이 21보다 크면 null`() {
        // given
        val cards = List(22) { createCard(rank = Rank.ACE) }
        // when
        val actual = pointCalculator.sumOrNull(cards)
        // then
        assertThat(actual).isNull()
    }

    @Test
    fun `에이스가 있을 때 - 21에 가장 가까운 수 반환 - 2`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.TEN))
        val expected = 21
        // when
        val actual = pointCalculator.sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있을 때 - 21에 가장 가까운 수 반환 - 3`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.ACE), createCard(rank = Rank.QUEEN))
        val expected = 12
        // when
        val actual = pointCalculator.sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있을 때 - 21에 가장 가까운 수 반환 - 4`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.ACE))
        val expected = 12
        // when
        val actual = pointCalculator.sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있을 때 - 21에 가장 가까운 수 반환 - 5`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.TEN))
        val expected = 21
        // when
        val actual = pointCalculator.sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
