package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GameResultTest {
    val participants = Participants(
        Players(
            listOf(
                Player(
                    Name("pobi"), Cards(
                        listOf(
                            Card(CardCategory.CLOVER, CardNumber.NINE),
                            Card(CardCategory.SPADE, CardNumber.NINE)
                        )
                    )
                ),
                Player(
                    Name("jason"), Cards(
                        listOf(
                            Card(CardCategory.CLOVER, CardNumber.SEVEN),
                            Card(CardCategory.SPADE, CardNumber.NINE)
                        )
                    )
                )
            )
        ),
        Dealer(
            Cards(
                listOf(
                    Card(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card(CardCategory.SPADE, CardNumber.NINE)
                )
            )
        )
    )

    @Test
    fun `플레이어들의 승패 정보를 반환한다`() {
        val gameResult = GameResult(participants)
        val actual = gameResult.getPlayersGameResult()
        assertAll(
            { assertThat(actual[Name("pobi")]).isEqualTo(GameResultType.승) },
            { assertThat(actual[Name("jason")]).isEqualTo(GameResultType.패) }
        )
    }

    @Test
    fun `딜러의 승패 정보를 반환한다`() {
        val gameResult = GameResult(participants)
        val actual = gameResult.getDealerGameResult()
        assertAll(
            { assertThat(actual[GameResultType.승]).isEqualTo(1) },
            { assertThat(actual[GameResultType.무]).isEqualTo(0) },
            { assertThat(actual[GameResultType.패]).isEqualTo(1) }
        )
    }
}
