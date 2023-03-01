package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PlayerHandTest {
    @Test
    fun `카드 목록에 카드를 추가한다`() {
        val playerHand = PlayerHand()

        playerHand.add(Card.of(1))
        playerHand.add(Card.of(3))
        playerHand.add(Card.of(5))

        assertThat(playerHand.cards.size).isEqualTo(3)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점 이하이면 11점으로 계산한다`() {
        val playerHand = PlayerHand()
        playerHand.add(Card.of(1))

        assertThat(playerHand.calculateTotalScore()).isEqualTo(11)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점을 초과하면 1점으로 계산한다`() {
        val playerHand = PlayerHand()
        playerHand.add(Card.of(1))
        playerHand.add(Card.of(14))

        assertThat(playerHand.calculateTotalScore()).isEqualTo(12)
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 12, 13])
    fun `J, Q, K는 모두 10점으로 계산한다`(number: Int) {
        val playerHand = PlayerHand()
        playerHand.add(Card.of(number))

        assertThat(playerHand.calculateTotalScore()).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource("2, 2", "3, 3", "4, 4", "5, 5", "6, 6", "7, 7", "8, 8", "9, 9", "10, 10")
    fun `2에서 10은 숫자 그대로 계산한다`(number: Int, expected: Int) {
        val playerHand = PlayerHand()
        playerHand.add(Card.of(number))

        assertThat(playerHand.calculateTotalScore()).isEqualTo(expected)
    }
}
