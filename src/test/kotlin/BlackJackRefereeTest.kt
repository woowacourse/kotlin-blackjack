import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackRefereeTest {

    @Test
    fun `딜러를 상대로 링링은 패배하고 우기는 승리하고 써니는 무승부`() {
        val woogi = Player(
            name = "woogi",
            cards = Cards(
                listOf(
                    Card(CardNumber.SEVEN, Shape.SPADE),
                    Card(CardNumber.K, Shape.SPADE)
                )
            )
        )

        val ring = Player(
            name = "ring",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.ONE, Shape.DIAMOND)
                )
            )
        )

        val sunny = Player(
            name = "sunny",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.TWO, Shape.DIAMOND)
                )
            )
        )

        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardNumber.SIX, Shape.DIAMOND),
                    Card(CardNumber.FOUR, Shape.CLOVER)
                )
            )
        )

        val blackJackReferee = BlackJackReferee()

        val actual = blackJackReferee.judgeGameResult(listOf(woogi, ring, sunny), dealer)

        assertThat(actual).isEqualTo(
            listOf(
                PlayerGameResult("woogi", GameResult.WIN),
                PlayerGameResult("ring", GameResult.LOSE),
                PlayerGameResult("sunny", GameResult.DRAW)
            )
        )
    }
}
