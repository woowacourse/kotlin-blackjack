package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `핸드는 카드를 추가한다`() {
        val hand = Hand(listOf(TestFixture.ClubTen, TestFixture.ClubJack))
        hand.addCard(TestFixture.ClubAce)
        assertThat(hand.cards.contains(TestFixture.ClubAce)).isTrue()
    }

    @Test
    fun `핸드는 카드의 합을 구한다`() {
        val hand = Hand(listOf(TestFixture.ClubTwo, TestFixture.ClubThree))
        val sum = hand.sum()
        val expected = TestFixture.ClubTwo.denomination.value + TestFixture.ClubThree.denomination.value
        assertThat(sum).isEqualTo(expected)
    }

    @Test
    fun `퀸 한장은 10점이다`() {
        val hand = Hand(listOf(TestFixture.ClubQueen))
        val sum = hand.sum()
        assertThat(sum).isEqualTo(10)
    }

    @Test
    fun `에이스 카드가 포함되어 있을 때, 다른 카드의 합이 11점 이하면 보너스 점수 10점을 획득한다`() {
        val hand = Hand(listOf(TestFixture.ClubAce, TestFixture.ClubTwo))
        val sum = hand.sum()
        assertThat(sum).isEqualTo(13)
    }

    @Test
    fun `에이스 카드가 포함되어 있을 때, 다른 카드의 합이 11점을 초과하면 총 보너스 점수가 없다`() {
        val hand = Hand(listOf(TestFixture.ClubAce, TestFixture.ClubKing, TestFixture.ClubEight))
        val sum = hand.sum()
        assertThat(sum).isEqualTo(19)
    }
}
