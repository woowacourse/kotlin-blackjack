package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackRefereeTest {
    @Test
    fun `카드 합이 18인 딜러를 상대로 카드 합이 17인 플레이어는 패배한다`() {
        val player = createPlayer(CardNumber.SEVEN, CardNumber.K)
        val dealer = createDealer(CardNumber.TEN, CardNumber.EIGHT)

        val actual = BlackJackReferee.judgeGameResult(player, dealer)

        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `카드 합이 18인 딜러를 상대로 카드 합이 20인 플레이어는 승리한다`() {
        val player = createPlayer(CardNumber.Q, CardNumber.K)
        val dealer = createDealer(CardNumber.TEN, CardNumber.EIGHT)

        val actual = BlackJackReferee.judgeGameResult(player, dealer)

        assertThat(actual).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `카드 합이 18인 딜러를 상대로 카드 합이 18인 플레이어는 무승부한다`() {
        val player = createPlayer(CardNumber.EIGHT, CardNumber.K)
        val dealer = createDealer(CardNumber.TEN, CardNumber.EIGHT)

        val actual = BlackJackReferee.judgeGameResult(player, dealer)

        assertThat(actual).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `딜러가 블랙잭이 아닌 경우 블랙잭인 플레이어는 승리한다`() {
        val player = createPlayer(CardNumber.A, CardNumber.K)
        val dealer = createDealer(CardNumber.TEN, CardNumber.EIGHT)

        val actual = BlackJackReferee.judgeGameResult(player, dealer)

        assertThat(actual).isEqualTo(GameResult.BLACKJACK)
    }

    private fun createPlayer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Player {
        return Player(PlayerName("name"), CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))), BetAmount(1000))
    }

    private fun createDealer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Dealer {
        return Dealer(CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))))
    }
}
