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
    fun `카드 합이 10인 딜러를 상대로 카드 합이 9인 플레이어는 패배하고 카드 합이 17인 플레이어는 승리하고 카드 합이 10인 플레이어는 무승부`() {
        val player1 = Player(
            name = PlayerName("player1"),
            cards = Cards(
                listOf(
                    Card(CardNumber.SEVEN, Shape.SPADE),
                    Card(CardNumber.K, Shape.SPADE)
                )
            )
        )

        val player2 = Player(
            name = PlayerName("player2"),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.ONE, Shape.DIAMOND)
                )
            )
        )

        val player3 = Player(
            name = PlayerName("player3"),
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

        val actual = blackJackReferee.judgeGameResult(listOf(player1, player2, player3), dealer)

        assertThat(actual).isEqualTo(
            listOf(
                PlayerGameResult("player1", GameResult.WIN),
                PlayerGameResult("player2", GameResult.LOSE),
                PlayerGameResult("player3", GameResult.DRAW)
            )
        )
    }
}
