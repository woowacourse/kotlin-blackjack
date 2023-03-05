package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `플레이어의 수는 1명이상 8명이하여야 한다`() {
        assertThrows<IllegalStateException> {
            Players(9) {
                Player(
                    Name("pobi"),
                    Cards(
                        Card(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card(CardCategory.SPADE, CardNumber.NINE)
                    )
                )
            }
        }
    }

    @Test
    fun `딜러와 플레이어들을 비교해서 플레이어들의 승패 결과를 얻는다`() {
        val players = Players(
            Player(
                Name("pobi"),
                Cards(
                    Card(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card(CardCategory.SPADE, CardNumber.NINE)
                )
            ),
            Player(
                Name("jason"),
                Cards(
                    Card(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card(CardCategory.SPADE, CardNumber.TWO)
                )
            )
        )
        val dealer = Dealer(
            Cards(
                Card(CardCategory.CLOVER, CardNumber.EIGHT),
                Card(CardCategory.SPADE, CardNumber.FOUR)
            )
        )
        val actual = players.result(dealer)
        val expected = mapOf(
            GameResult.WIN to 1,
            GameResult.LOSE to 1
        )
        assertThat(actual).isEqualTo(expected)
    }
}
