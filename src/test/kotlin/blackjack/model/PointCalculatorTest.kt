package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointCalculatorTest {
    @Test
    fun `21에 가장 가까운 수 반환 - 1`() {
        // given
        val cards = listOf(createCard(rank = Rank.Six), createCard(rank = Rank.Five))
        val expected = 11
        // when
        val actual = sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `21에 가장 가까운 수 반환 - 2`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.Ten))
        val expected = 21
        // when
        val actual = sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `21에 가장 가까운 수 반환 - 3`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.ACE), createCard(rank = Rank.Queen))
        val expected = 12
        // when
        val actual = sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `21에 가장 가까운 수 반환 - 4`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.ACE))
        val expected = 12
        // when
        val actual = sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `21에 가장 가까운 수 반환 - 5`() {
        // given
        val cards = listOf(createCard(rank = Rank.ACE), createCard(rank = Rank.Ten))
        val expected = 21
        // when
        val actual = sumOrNull(cards)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있을 때 - 카드의 합이 21보다 크면 null`() {
        // given
        val cards = List(22) { createCard(rank = Rank.ACE) }
        // when
        val actual = sumOrNull(cards)
        // then
        assertThat(actual).isNull()
    }

    @Test
    fun `에이스가 없을 때 - 카드의 합이 21보다 크면 null`() {
        // given
        val cards = List(3) { createCard(rank = Rank.Queen) }
        // when
        val actual = sumOrNull(cards)
        // then
        assertThat(actual).isNull()
    }
}

fun sumOrNull(cards: List<Card>): Int? {
    if (cards.all { !it.isAce() }) {
        val sum = cards.sumOf { it.rank.point }
        if (sum > 21) return null
        return sum
    }
    val (aceCards, generalCards) = cards.partition { it.isAce() }
    val sumOfNotAce = generalCards.sumOf { it.rank.point }
    // ///////
    val condition = 21
    val a = aceCards.size + sumOfNotAce
    val b = a + 10
    if (a > condition) return null
    if (b <= condition) return b
    return a
}
