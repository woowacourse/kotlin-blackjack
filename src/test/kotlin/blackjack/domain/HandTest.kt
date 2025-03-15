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
        val hand = Hand(listOf(TestFixture.ClubTen, TestFixture.ClubJack))
        val sum = hand.sum()
        val expected = TestFixture.ClubTen.denomination.value + TestFixture.ClubJack.denomination.value
        assertThat(sum).isEqualTo(expected)
    }
}