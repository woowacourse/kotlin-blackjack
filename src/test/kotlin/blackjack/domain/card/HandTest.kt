package blackjack.domain.card

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class HandTest {

    @Test
    fun `핸드의 점수는 카드 가치의 총합과 같다`() {
        val hand = Hand(listOf(Card(CardNumber.TWO, CardShape.HEART), Card(CardNumber.SEVEN, CardShape.CLOVER)))

        val actual = hand.getScore()
        val expect = CardNumber.TWO.value + CardNumber.SEVEN.value

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `핸드에 ACE 카드가 포함되어 있고 카드 가치의 총합이 21을 초과한다면 ACE의 가치를 1로 변경할 수 있다`() {
        val hand = Hand(
            listOf(
                Card(CardNumber.KING, CardShape.HEART),
                Card(CardNumber.KING, CardShape.CLOVER),
                Card(CardNumber.ACE, CardShape.CLOVER),
            ),
        )

        val actual = hand.getScore()
        val expect = CardNumber.KING.value + CardNumber.KING.value + 1

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `핸드의 점수가 21이라면 블랙잭이다`() {
        val hand = Hand(
            listOf(
                Card(CardNumber.KING, CardShape.HEART),
                Card(CardNumber.ACE, CardShape.CLOVER),
            ),
        )

        assertThat(hand.isBlackjack()).isTrue
    }

    @Test
    fun `핸드의 점수가 21을 초과한다면 버스트이다`() {
        val hand = Hand(
            listOf(
                Card(CardNumber.KING, CardShape.HEART),
                Card(CardNumber.KING, CardShape.CLOVER),
                Card(CardNumber.KING, CardShape.CLOVER),
            ),
        )

        assertThat(hand.isBust()).isTrue
    }
}
