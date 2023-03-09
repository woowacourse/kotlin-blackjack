package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `처음에 패를 두 장 보여준다`() {
        val player = Player(
            Name("scott"),
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
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
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
            ),
            BettingMoney(1000),
        )
        val actual = player.isPossibleDrawCard()
        assertThat(actual).isTrue
    }

    @Test
    fun `플레이어의 점수가 Burst 이면 딜러의 점수와 상관없이 패배이다`() {
        val player = Player(
            Name("scott"),
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.QUEEN),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
            ),
            BettingMoney(1000),
        )
        val opponentScore = Score(23, false)
        val result = player.getGameResult(opponentScore)
        val expected = GameResultType.LOSE
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 점수가 Burst가 아니고, 딜러의 점수만 Burst면 승리이다`() {
        val player = Player(
            Name("scott"),
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.QUEEN),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
            ),
            BettingMoney(1000),
        )
        val opponentScore = Score(23, false)
        val result = player.getGameResult(opponentScore)
        val expected = GameResultType.WIN
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `"Burst되지 않은 동일한 점수일때, 승패를 확인하면, 무승부이다"`() {
        val player = Player(
            Name("scott"),
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
            ),
            BettingMoney(1000),
        )
        val opponentScore = Score(17, false)
        val result = player.getGameResult(opponentScore)
        val expected = GameResultType.DRAW
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `Burst되지 않은 동일한 점수일때, 딜러의 점수가 플레이어의 점수보다 크디면 패배이다`() {
        val player = Player(
            Name("scott"),
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
            ),
            BettingMoney(1000),
        )
        val opponentScore = Score(18, false)
        val result = player.getGameResult(opponentScore)
        val expected = GameResultType.LOSE
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `Burst되지 않은 동일한 점수일때, 딜러의 점수가 플레이어의 점수보다 작다면 승리이다`() {
        val player = Player(
            Name("scott"),
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
            ),
            BettingMoney(1000),
        )
        val opponentScore = Score(16, false)
        val result = player.getGameResult(opponentScore)
        val expected = GameResultType.WIN
        assertThat(result).isEqualTo(expected)
    }
}
