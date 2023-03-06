package domain

import blackjack.domain.BlackJackReferee
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerName
import blackjack.domain.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackRefereeTest {

    @Test
    fun `카드 합이 10인 딜러를 상대로 카드 합이 9인 링링은 패배하고 카드 합이 17인 우기는 승리하고 카드 합이 10인 써니는 무승부`() {
        val woogi = Player(
            name = PlayerName("woogi"),
            cards = Cards(
                listOf(
                    Card(CardNumber.SEVEN, Shape.SPADE),
                    Card(CardNumber.K, Shape.SPADE)
                )
            )
        )

        val ring = Player(
            name = PlayerName("ring"),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.ONE, Shape.DIAMOND)
                )
            )
        )

        val sunny = Player(
            name = PlayerName("sunny"),
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
