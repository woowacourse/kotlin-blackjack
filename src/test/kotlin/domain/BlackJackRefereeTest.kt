package domain

import blackjack.domain.BlackJackReferee
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackRefereeTest {
    private fun createPlayer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Player {
        return Player(PlayerName("name"), Cards(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))))
    }

    private fun createDealer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Dealer {
        return Dealer(Cards(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))))
    }

    @Test
    fun `카드 합이 10인 딜러를 상대로 카드 합이 9인 플레이어는 패배하고 카드 합이 17인 플레이어는 승리하고 카드 합이 10인 플레이어는 무승부`() {
        // given
        val player1 = createPlayer(CardNumber.SEVEN, CardNumber.K) // 17
        val player2 = createPlayer(CardNumber.EIGHT, CardNumber.ONE) // 9
        val player3 = createPlayer(CardNumber.EIGHT, CardNumber.TWO) // 10
        val dealer = createDealer(CardNumber.SIX, CardNumber.FOUR) // 10
        val blackJackReferee = BlackJackReferee()

        // when
        val actual = blackJackReferee.judgeGameResult(
            players = listOf(player1, player2, player3),
            dealer = dealer
        )

        // then
        val (player1Result, player2Result, player3Result) = actual
        assertThat(player1Result.gameResult).isEqualTo(GameResult.WIN)
        assertThat(player2Result.gameResult).isEqualTo(GameResult.LOSE)
        assertThat(player3Result.gameResult).isEqualTo(GameResult.DRAW)
    }
}
