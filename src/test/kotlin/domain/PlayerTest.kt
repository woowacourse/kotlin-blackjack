package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    private fun Cards(vararg cards: Int): Cards {
        return Cards(
            cards.map { number ->
                Card.of(
                    CardCategory.CLOVER,
                    CardNumber.values().find { it.value == number } ?: CardNumber.FIVE,
                )
            },
        )
    }

    @Test
    fun `처음에 패를 두 장 보여준다`() {
        val player = Player(
            Name("scott"),
            Cards(
                8,
                9,
            ),
            BettingMoney(1000),
        )

        val actual = player.showInitCards().size
        val expected = 2
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `21보다 작으면 더 받을 수 있도록 true를 반환한다`() {
        val player = Player(
            Name("scott"),
            Cards(
                8,
                9,
            ),
            BettingMoney(1000),
        )
        val actual = player.isPossibleDrawCard()
        assertThat(actual).isTrue
    }

    @Test
    fun `승리 일 경우에는 베팅머니의 1배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            Cards(
                8,
                9,
            ),
            BettingMoney(1000),
        )
        val dealerScore = Score(Cards(2, 10))
        // when
        val actual = player.getProfit(dealerScore)
        val expected = 1000
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `패배일 경우에는 베팅머니의 -1배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            Cards(
                8,
                9,
            ),
            BettingMoney(1000),
        )
        val dealerScore = Score(Cards(9, 10))
        // when
        val actual = player.getProfit(dealerScore)
        val expected = -1000
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무승부인 경우에는 베팅머니의 0배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            Cards(
                8,
                9,
            ),
            BettingMoney(1000),
        )
        val dealerScore = Score(Cards(7, 10))
        // when
        val actual = player.getProfit(dealerScore)
        val expected = 0
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `블랙잭인 경우에는 베팅머니의 2분의3배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            Cards(
                1,
                10,
            ),
            BettingMoney(1000),
        )
        val dealerScore = Score(Cards(2, 10))
        // when
        val actual = player.getProfit(dealerScore)
        val expected = 1500
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
