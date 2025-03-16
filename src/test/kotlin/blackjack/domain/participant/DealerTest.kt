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
import blackjack.fixture.SIX_SPADE
import blackjack.fixture.TWO_SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player("포르")
    }

    @Test
    fun `딜러가 카드를 한 장 지급 받으면 딜러의 패는 한 장이다`() {
        dealer.receiveCard(ACE_SPADE)
        assertThat(dealer.hand.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러가 Ace 한 장과 Queen 한 장을 가지면 점수는 21이다`() {
        dealer.receiveCard(ACE_SPADE)
        dealer.receiveCard(QUEEN_SPADE)

        assertThat(dealer.score().score).isEqualTo(21)
    }

    @Test
    fun `딜러가 Ace 두 장과 9 한 장을 가지면 점수는 21이다`() {
        dealer.receiveCard(ACE_SPADE)
        dealer.receiveCard(ACE_CLUB)
        dealer.receiveCard(NINE_SPADE)

        assertThat(dealer.score().score).isEqualTo(21)
    }

    @Test
    fun `딜러 점수가 16이면 카드를 더 뽑을 수 있다`() {
        dealer.drawCards(QUEEN_SPADE, SIX_SPADE)
        assertThat(dealer.canHit()).isTrue()
    }

    @Test
    fun `딜러 점수가 17이면 카드를 더 뽑을 수 없다`() {
        dealer.drawCards(QUEEN_SPADE, SEVEN_SPADE)
        assertThat(dealer.canHit()).isFalse()
    }

    @Test
    fun `딜러의 점수가 21이고 상대 점수가 20이면 딜러가 이긴다`() {
        dealer.drawCards(QUEEN_SPADE, ACE_SPADE)
        player.drawCards(QUEEN_SPADE, JACK_SPADE)

        val result = dealer.resultAgainst(player)
        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러의 점수가 17이고 상대 점수가 20이면 딜러가 진다`() {
        dealer.drawCards(QUEEN_SPADE, SEVEN_SPADE)
        player.drawCards(QUEEN_SPADE, JACK_SPADE)

        val result = dealer.resultAgainst(player)
        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러의 점수가 22이고 상대 점수가 22이면 딜러가 이긴다`() {
        dealer.drawCards(QUEEN_SPADE, JACK_SPADE, TWO_SPADE)
        player.drawCards(QUEEN_SPADE, JACK_SPADE, TWO_SPADE)

        val result = dealer.resultAgainst(player)
        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러의 점수가 20이고 상대 점수가 20이면 비긴다`() {
        dealer.drawCards(QUEEN_SPADE, JACK_SPADE)
        player.drawCards(QUEEN_SPADE, QUEEN_CLUB)

        val result = dealer.resultAgainst(player)
        assertThat(result).isEqualTo(GameResult.PUSH)
    }

    private fun Participant.drawCards(vararg cards: Card) {
        cards.forEach { this.receiveCard(it) }
    }
}
