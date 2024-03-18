package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
        val actual = pointCalculator.sumOf(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "ACE,TEN,21",
        "ACE,ACE,12",
    )
    fun `에이스가 있을 때 - 21에 가장 가까운 수 반환`(
        rank: Rank,
        rank2: Rank,
        expectedSum: Int,
    ) {
        // given
        val cards = listOf(createCard(rank = rank), createCard(rank = rank2))
        // when
        val actual = pointCalculator.sumOf(cards)
        // then
        assertThat(actual).isEqualTo(expectedSum)
    }
}
