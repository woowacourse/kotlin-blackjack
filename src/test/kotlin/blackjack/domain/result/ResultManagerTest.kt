package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerInfo
import blackjack.domain.participant.Players
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResultManagerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player
    private lateinit var resultManager: ResultManager

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player(PlayerInfo("글로", 1000))
        resultManager = ResultManager(dealer, Players(listOf(player)))
    }

    @Test
    fun `딜러와 플레이어의 카드를 반환한다`() {
        val soft = SoftAssertions()

        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.HEART))
            addCard(Card(CardNumber.ACE, Suit.HEART))
        }
        with(player) {
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }

        val results = resultManager.judge()

        val expectedDealer = listOf(Card(CardNumber.QUEEN, Suit.SPADE), Card(CardNumber.ACE, Suit.SPADE))
        soft.assertThat(results.dealerResult.cards).isEqualTo(expectedDealer)

        val expectedPlayer = listOf(Card(CardNumber.JACK, Suit.HEART), Card(CardNumber.ACE, Suit.HEART))
        soft.assertThat(results.playerResults[0].cards).isEqualTo(expectedPlayer)
    }

    @Test
    fun `딜러와 플레이어의 점수를 반환한다`() {
        val soft = SoftAssertions()

        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.HEART))
            addCard(Card(CardNumber.ACE, Suit.HEART))
        }
        with(player) {
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.KING, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.score
        soft.assertThat(actualDealer).isEqualTo(21)

        val actualPlayer = results.playerResults[0].score
        soft.assertThat(actualPlayer).isEqualTo(20)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭이면 무승부이다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.HEART))
            addCard(Card(CardNumber.ACE, Suit.HEART))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.draw
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `딜러만 블랙잭이면 딜러는 승리하고 플레이어는 패배한다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.NINE, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.HEART))
            addCard(Card(CardNumber.ACE, Suit.HEART))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.win
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트면 딜러는 승리하고 플레이어는 패배한다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.KING, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.HEART))
            addCard(Card(CardNumber.KING, Suit.HEART))
            addCard(Card(CardNumber.TWO, Suit.HEART))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.win
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어가 21점을 초과하면 딜러는 승리하고 플레이어는 패배한다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.NINE, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.KING, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.win
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러만 21점을 초과하면 딜러는 패배하고 플레이어는 승리한다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.NINE, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.KING, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.lose
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어가 21점을 초과하지 않고 플레이어와 딜러의 점수가 같으면 무승부이다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.HEART))
            addCard(Card(CardNumber.QUEEN, Suit.HEART))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.draw
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `플레이어가 21점을 초과하지 않고 처음 받은 카드 두 장이 21점이면 딜러는 패배이고 플레이어는 블랙잭이다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.FIVE, Suit.SPADE))
            addCard(Card(CardNumber.SIX, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.lose
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.BLACKJACK)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 블랙잭이 아니면서 플레이어가 딜러보다 점수가 높으면 딜러는 패배하고 플레이어는 승리한다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.EIGHT, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.lose
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 블랙잭이 아니면서 딜러가 플레이어보다 점수가 높으면 딜러는 승리하고 플레이어는 패배한다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.JACK, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.win
        soft.assertThat(actualDealer).isEqualTo(1)

        val actualPlayer = results.playerResults[0].gameResult
        soft.assertThat(actualPlayer).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어가 블랙잭이면 플레이어가 딜러로부터 배팅 금액의 3분의 2배를 얻는다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.FIVE, Suit.SPADE))
            addCard(Card(CardNumber.SIX, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.profit
        soft.assertThat(actualDealer).isEqualTo(-1500)

        val actualPlayer = results.playerResults[0].profit
        soft.assertThat(actualPlayer).isEqualTo(1500)
    }

    @Test
    fun `플레이어가 우승하면 플레이어가 딜러로부터 배팅 금액을 받는다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.EIGHT, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.profit
        soft.assertThat(actualDealer).isEqualTo(-1000)

        val actualPlayer = results.playerResults[0].profit
        soft.assertThat(actualPlayer).isEqualTo(1000)
    }

    @Test
    fun `무승부면 딜러와 플레이어 모두 수익이 없다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.HEART))
            addCard(Card(CardNumber.QUEEN, Suit.HEART))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.profit
        soft.assertThat(actualDealer).isEqualTo(0)

        val actualPlayer = results.playerResults[0].profit
        soft.assertThat(actualPlayer).isEqualTo(0)
    }

    @Test
    fun `플레이어가 패배하면 플레이어는 딜러에게 배팅 금액을 준다`() {
        val soft = SoftAssertions()

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.JACK, Suit.SPADE))
        }

        val results = resultManager.judge()

        val actualDealer = results.dealerResult.profit
        soft.assertThat(actualDealer).isEqualTo(1000)

        val actualPlayer = results.playerResults[0].profit
        soft.assertThat(actualPlayer).isEqualTo(-1000)
    }
}
