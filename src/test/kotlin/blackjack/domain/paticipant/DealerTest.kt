package blackjack.domain.paticipant

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerInfo
import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {
    lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러는 이름을 갖는다`() {
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러가 처음 공개할 카드는 1장이다`() {
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        assertThat(dealer.getFirstOpenCards().size).isEqualTo(1)
    }

    @Test
    fun `딜러는 자신이 처음 공개할 카드를 반환한다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.FIVE, Suit.SPADE))

        assertThat(dealer.getFirstOpenCards()).isEqualTo(listOf(Card(CardNumber.ACE, Suit.SPADE)))
    }

    @Test
    fun `딜러는 카드의 합이 16점 이하면 카드를 뽑을 수 있다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.FIVE, Suit.SPADE))

        assertThat(dealer.canDraw()).isTrue
    }

    @Test
    fun `딜러는 카드의 합이 17점 이상이면 더 이상 카드를 뽑을 수 없다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.SIX, Suit.SPADE))

        assertThat(dealer.canDraw()).isFalse
    }

    @Test
    fun `딜러는 카드 목록에 카드를 추가한다`() {
        dealer.addCard(Card(CardNumber.TWO, Suit.SPADE))

        assertThat(dealer.getCards()).containsExactly(Card(CardNumber.TWO, Suit.SPADE))
    }

    @Test
    fun `딜러가 보유한 카드를 반환한다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.JACK, Suit.SPADE))

        assertThat(dealer.getCards()).isEqualTo(
            listOf(
                Card(CardNumber.ACE, Suit.SPADE),
                Card(CardNumber.JACK, Suit.SPADE)
            )
        )
    }

    @ParameterizedTest
    @CsvSource(
        "SPADE, ACE, HEART, ACE, 12",
        "SPADE, ACE, SPADE, JACK, 21",
        "SPADE, TEN, HEART, SEVEN, 17"
    )
    fun `자신의 점수를 반환한다`(
        firstCardSuit: Suit,
        firstCardNumber: CardNumber,
        secondCardSuit: Suit,
        secondCardNumber: CardNumber,
        expected: Int
    ) {
        dealer.addCard(Card(firstCardNumber, firstCardSuit))
        dealer.addCard(Card(secondCardNumber, secondCardSuit))

        assertThat(dealer.getTotalScore()).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭이면 무승부이다`() {
        val player = Player(PlayerInfo("glo", 0))

        with(player) {
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.HEART))
            addCard(Card(CardNumber.ACE, Suit.HEART))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `딜러만 블랙잭이면 플레이어는 패배한다`() {
        val player = Player(PlayerInfo("glo", 0))

        with(player) {
            addCard(Card(blackjack.domain.card.CardNumber.NINE, blackjack.domain.card.Suit.SPADE))
            addCard(Card(blackjack.domain.card.CardNumber.TWO, blackjack.domain.card.Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트면 플레이어가 패배한다`() {
        val player = Player(PlayerInfo("glo", 0))

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

        assertThat(dealer judge player).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어가 21점을 초과하면 패배한다`() {
        val player = Player(PlayerInfo("glo", 0))

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

        assertThat(dealer judge player).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러만 21점을 초과하면 패배한다`() {
        val player = Player(PlayerInfo("glo", 0))

        with(player) {
            addCard(Card(CardNumber.NINE, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.KING, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어가 21점을 초과하지 않고 플레이어와 딜러의 점수가 같으면 무승부이다`() {
        val player = Player(PlayerInfo("glo", 0))

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `플레이어가 21점을 초과하지 않고 처음 받은 카드 두 장이 21점이면 블랙잭이다`() {
        val player = Player(PlayerInfo("glo", 0))

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

        assertThat(dealer judge player).isEqualTo(GameResult.BLACKJACK)
    }

    @Test
    fun `플레이어가 21점을 초과하지 않고 블랙잭이 아니면서 딜러가 21점을 초과하면 플레이어가 승리한다`() {
        val player = Player(PlayerInfo("glo", 0))

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.DIAMOND))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.KING, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 블랙잭이 아니면서 플레이어가 딜러보다 점수가 높으면 플레이어가 승리한다`() {
        val player = Player(PlayerInfo("glo", 0))

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.EIGHT, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 블랙잭이 아니면서 딜러가 플레이어보다 점수가 높으면 플레이어가 패배한다`() {
        val player = Player(PlayerInfo("glo", 0))

        with(player) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
        }
        with(dealer) {
            addCard(Card(CardNumber.ACE, Suit.SPADE))
            addCard(Card(CardNumber.JACK, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.LOSE)
    }
}
