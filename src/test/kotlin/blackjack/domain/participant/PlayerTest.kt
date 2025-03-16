package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.card.Card
import blackjack.fixture.ACE_CLUB
import blackjack.fixture.ACE_SPADE
import blackjack.fixture.JACK_SPADE
import blackjack.fixture.NINE_SPADE
import blackjack.fixture.QUEEN_CLUB
import blackjack.fixture.QUEEN_SPADE
import blackjack.fixture.SEVEN_SPADE
import blackjack.fixture.TWO_SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player("Jason")
        dealer = Dealer()
    }

    @Test
    fun `플레이어가 카드를 한 장 지급 받으면 플레이어의 패는 한 장이다`() {
        player.receiveCard(ACE_SPADE)
        assertThat(player.hand.cards.size).isEqualTo(1)
    }

    @Test
    fun `플레이어가 Ace 한 장과 Queen 한 장을 가지면 점수는 21이다`() {
        player.drawCards(ACE_SPADE, QUEEN_SPADE)

        val score = player.score()
        assertThat(score.score).isEqualTo(21)
    }

    @Test
    fun `플레이어가 Ace 두 장과 9 한 장을 가지면 점수는 21이다`() {
        player.drawCards(ACE_SPADE, ACE_CLUB, NINE_SPADE)

        val score = player.score()
        assertThat(score.score).isEqualTo(21)
    }

    @Test
    fun `플레이어 점수가 21이면 카드를 더 뽑을 수 있다`() {
        player.drawCards(QUEEN_SPADE, ACE_SPADE)
        assertThat(player.canHit()).isTrue()
    }

    @Test
    fun `플레이어 점수가 22이면 카드를 더 뽑을 수 없다`() {
        player.drawCards(QUEEN_SPADE, QUEEN_CLUB, TWO_SPADE)
        assertThat(player.canHit()).isFalse()
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 17이면 플레이어가 이긴다`() {
        player.drawCards(QUEEN_SPADE, QUEEN_CLUB)
        dealer.drawCards(QUEEN_SPADE, SEVEN_SPADE)

        val result = player.resultAgainst(dealer)
        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 21이면 플레이어가 진다`() {
        player.drawCards(QUEEN_SPADE, QUEEN_CLUB)
        dealer.drawCards(QUEEN_SPADE, ACE_SPADE)

        val result = player.resultAgainst(dealer)
        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 22이면 플레이어가 이긴다`() {
        player.drawCards(QUEEN_SPADE, JACK_SPADE)
        dealer.drawCards(QUEEN_SPADE, JACK_SPADE, TWO_SPADE)

        val result = player.resultAgainst(dealer)
        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 20이면 비긴다`() {
        player.drawCards(QUEEN_SPADE, JACK_SPADE)
        dealer.drawCards(QUEEN_SPADE, QUEEN_CLUB)

        val result = player.resultAgainst(dealer)
        assertThat(result).isEqualTo(GameResult.PUSH)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러의 점수가 20이면 플레이어가 블랙잭으로 이긴다`() {
        player.drawCards(ACE_SPADE, QUEEN_SPADE)
        dealer.drawCards(QUEEN_SPADE, QUEEN_CLUB)

        val result = player.resultAgainst(dealer)
        assertThat(result).isEqualTo(GameResult.WIN_BLACKJACK)
    }

    @Test
    fun `플레이어가 카드 뽑기를 한 번 하면 카드를 한 장 뽑는다`() {
        // given
        var firstCall = true
        val shouldContinue: (Participant) -> Boolean = {
            if (firstCall) {
                firstCall = false
                true
            } else {
                false
            }
        }

        // when
        player.playGame(
            draw = { ACE_SPADE },
            shouldContinue = shouldContinue,
            onDraw = {},
        )

        // then
        assertThat(player.hand.cards.size).isEqualTo(1)
    }

    private fun Participant.drawCards(vararg cards: Card) {
        cards.forEach { this.receiveCard(it) }
    }
}
