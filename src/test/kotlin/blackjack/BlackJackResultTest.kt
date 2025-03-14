package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.gameResult.BlackJackResult
import blackjack.domain.gameResult.GameResult
import blackjack.domain.gameResult.PlayerResult
import blackjack.domain.gameResult.state.Stay
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.fixture.Fixture.BLACK_JACK
import blackjack.fixture.Fixture.BUST
import blackjack.fixture.Fixture.TWENTY_ONE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackResultTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player
    private val bettingAmount = 5000

    @BeforeEach
    fun clear() {
        dealer = Dealer()
        player = Player("a", bettingAmount)
    }

    private fun setDealerCard(vararg card: Card) {
        card.forEach { dealer.addCard(it) }
    }

    private fun setPlayerCard(vararg card: Card) {
        card.forEach { player.addCard(it) }
    }

    @Test
    fun `점수를 판별할 때, 플레이어가 딜러보다 더 점수가 높을 때 이긴다고 판단한다`() {
        setDealerCard(
            Card.of(Rank.NINE, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )

        setPlayerCard(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        val playerGameResult =
            PlayerResult(
                Stay(player),
                GameResult.WIN,
            )
        assertThat(blackJackResult.playerResults[0]).isEqualTo(playerGameResult)
    }

    @Test
    fun `점수를 판별할 때, 플레이어가 딜러보다 더 점수가 높을 때 진다고 판단한다`() {
        setDealerCard(
            *BLACK_JACK,
        )

        setPlayerCard(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        val playerGameResult =
            PlayerResult(
                Stay(player),
                GameResult.LOSE,
            )
        assertThat(blackJackResult.playerResults[0]).isEqualTo(playerGameResult)
    }

    @Test
    fun `점수를 판별할 때, 플레이어와 딜러의 점수가 같다면 무승부라고 판단한다`() {
        setDealerCard(
            *TWENTY_ONE,
        )

        setPlayerCard(
            *TWENTY_ONE,
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        val playerGameResult =
            PlayerResult(
                Stay(player),
                GameResult.DRAW,
            )
        assertThat(blackJackResult.playerResults[0]).isEqualTo(playerGameResult)
    }

    @Test
    fun `점수를 판별할 때, 블랙잭은 21보다 높다고 판단한다`() {
        setDealerCard(
            *BLACK_JACK,
        )

        setPlayerCard(
            *TWENTY_ONE,
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        val playerGameResult =
            PlayerResult(
                Stay(player),
                GameResult.LOSE,
            )
        assertThat(blackJackResult.playerResults[0]).isEqualTo(playerGameResult)
    }

    @Test
    fun `점수를 판별할 떄, 플레이어가 딜러보다 점수가 높으면 베팅한 금액만큼 수익을 얻는다`() {
        setDealerCard(
            Card.of(Rank.EIGHT, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )

        setPlayerCard(
            *TWENTY_ONE,
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(5000)
    }

    @Test
    fun `점수를 판별할 떄, 플레이어가 딜러보다 점수가 낮으면 베팅한 금액만큼 금액을 잃는다`() {
        setDealerCard(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )

        setPlayerCard(
            Card.of(Rank.TEN, Suit.SPADE),
            Card.of(Rank.NINE, Suit.SPADE),
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(-5000)
    }

    @Test
    fun `점수를 판별할 떄, 플레이어가 블랙잭이면 베팅한 금액의 1,5배를 돌려받는다`() {
        setDealerCard(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )

        setPlayerCard(
            *BLACK_JACK,
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(7500)
    }

    @Test
    fun `점수를 판별할 떄, 딜러가 블랙잭이면 베팅한 금액만큼 잃는다`() {
        setDealerCard(
            *BLACK_JACK,
        )

        setPlayerCard(
            Card.of(Rank.TEN, Suit.SPADE),
            Card.of(Rank.EIGHT, Suit.SPADE),
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(-5000)
    }

    @Test
    fun `점수를 판별할 떄, 플레이어가 블랙잭이면 딜러가 블랙잭이 아닌 21이 되어도 베팅한 금액의 1,5배를 돌려받는다`() {
        setDealerCard(
            *TWENTY_ONE,
        )

        setPlayerCard(
            *BLACK_JACK,
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(7500)
    }

    @Test
    fun `점수를 판별할 떄, 딜러와 플레이어가 서로 점수가 같으면 0원을 받는다`() {
        setDealerCard(
            Card.of(Rank.SEVEN, Suit.HEART),
            Card.of(Rank.TEN, Suit.CLUB),
        )

        setPlayerCard(
            Card.of(Rank.TWO, Suit.SPADE),
            Card.of(Rank.ACE, Suit.SPADE),
            Card.of(Rank.FOUR, Suit.SPADE),
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(0)
    }

    @Test
    fun `점수를 판별할 떄, 딜러와 플레이어가 서로 블랙잭이면 0원을 받는다`() {
        setDealerCard(
            *BLACK_JACK,
        )

        setPlayerCard(
            *BLACK_JACK,
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(0)
    }

    @Test
    fun `점수를 판별할 떄, 플레이어가 버스트이면 딜러의 결과에 상관없이 베팅 금액을 잃는다`() {
        setDealerCard(
            *BUST,
        )

        setPlayerCard(
            *BUST,
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(-5000)
    }

    @Test
    fun `점수를 판별할 떄, 딜러가 버스트이면 베팅 금액만큼 얻는다`() {
        setDealerCard(
            *BUST,
        )

        setPlayerCard(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )
        val blackJackResult = BlackJackResult(dealer, listOf(player))
        assertThat(blackJackResult.playerResults[0].getProfit()).isEqualTo(-5000)
    }
}
