package domain

import blackjack.domain.BattingMoney
import blackjack.domain.BlackJackReferee
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Shape
import blackjack.domain.dealer.Dealer
import blackjack.domain.gameResult.GameResult
import blackjack.domain.gameResult.PlayerGameResult
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackRefereeTest {

    @Test
    fun `딜러를 상대로 링링은 패배하고 우기는 승리하고 써니는 무승부`() {
        val woogi = Player(
            name = PlayerName("woogi"),
            battingMoney = BattingMoney(1000),
            cards = Cards(
                listOf(
                    Card(CardNumber.SEVEN, Shape.SPADE),
                    Card(CardNumber.K, Shape.SPADE)
                )
            )
        )

        val ring = Player(
            name = PlayerName("ring"),
            battingMoney = BattingMoney(1000),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.ONE, Shape.DIAMOND)
                )
            )
        )

        val sunny = Player(
            name = PlayerName("sunny"),
            battingMoney = BattingMoney(1000),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.TWO, Shape.DIAMOND)
                )
            )
        )

        val dealer = Dealer(
            battingMoney = BattingMoney(1000),
            Cards(
                listOf(
                    Card(CardNumber.SIX, Shape.DIAMOND),
                    Card(CardNumber.FOUR, Shape.CLOVER)
                )
            )
        )

        val blackJackReferee = BlackJackReferee()

        val actual = blackJackReferee.judgeTotalGameResults(listOf(woogi, ring, sunny), dealer)

        assertThat(actual).isEqualTo(
            TotalGameResult(
                playersGameResult = listOf(
                    PlayerGameResult("woogi", GameResult.WIN),
                    PlayerGameResult("ring", GameResult.LOSE),
                    PlayerGameResult("sunny", GameResult.DRAW)
                ),
                dealerGameResults = listOf(
                    GameResult.LOSE,
                    GameResult.WIN,
                    GameResult.DRAW
                )
            )
        )
    }
}
