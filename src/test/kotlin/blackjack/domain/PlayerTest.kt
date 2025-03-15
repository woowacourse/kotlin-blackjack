package blackjack.domain

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.fixture.blackJackCardFixture
import blackjack.fixture.bustTrumpCardFixture
import blackjack.fixture.minCardFixture
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player("peto")
    }

    @Test
    fun `플레이어가 게임을 시작하면 카드를 2장 지급받는다`() {
        val fixture = trumpCardFixture()
        fixture.forEach {
            player.addCard(it)
        }
        assertThat(player.cards.items).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `플레이어 카드의 총합이 21을 초과하면 카드를 더 뽑을 수 없다`() {
        bustTrumpCardFixture().forEach {
            player.addCard(it)
        }
        assertEquals(player.canHit(), false)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되지 않았으면 카드 총합에 10을 더한다`() {
        player.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        player.addCard(TrumpCard(Tier.NINE, Shape.HEART))

        assertEquals(player.totalScore(), 20)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되었으면 카드 총합을 유지한다`() {
        player.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        player.addCard(TrumpCard(Tier.SEVEN, Shape.HEART))
        player.addCard(TrumpCard(Tier.NINE, Shape.HEART))

        assertEquals(player.totalScore(), 17)
    }

    @Test
    fun `최초에 카드를 받은 후 오픈할 카드 2장을 반환한다`() {
        player.addCard(TrumpCard(Tier.SEVEN, Shape.HEART))
        player.addCard(TrumpCard(Tier.ACE, Shape.DIA))

        assertThat(player.getInitialCards())
            .containsExactly(TrumpCard(Tier.SEVEN, Shape.HEART), TrumpCard(Tier.ACE, Shape.DIA))
    }

    @Test
    fun `플레이어와 딜러가 모두 블랙잭이면 무승부한다`() {
        blackJackCardFixture().forEach {
            player.addCard(it)
            dealer.addCard(it)
        }

        val result = player.compare(dealer)

        assertEquals(GameResult.PUSH, result)
    }

    @Test
    fun `플레이어와 딜러가 모두 버스트되면 플레이어는 패배한다`() {
        bustTrumpCardFixture().forEach {
            player.addCard(it)
            dealer.addCard(it)
        }

        val result = player.compare(dealer)

        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `플레이어가 버스트되면 패배한다`() {
        bustTrumpCardFixture().forEach(player::addCard)
        trumpCardFixture().forEach(dealer::addCard)

        val result = player.compare(dealer)

        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 플레이어는 블랙잭 상태로 승리 한다`() {
        blackJackCardFixture().forEach(player::addCard)
        trumpCardFixture().forEach(dealer::addCard)

        val result = player.compare(dealer)

        assertEquals(GameResult.BLACKJACK, result)
    }

    @Test
    fun `딜러가 버스트 되고 플레이어가 버스트 되지 않으면 플레이어가 승리한다`() {
        trumpCardFixture().forEach(player::addCard)
        bustTrumpCardFixture().forEach(dealer::addCard)

        val result = player.compare(dealer)

        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 높으면 플레이어가 승리한다`() {
        trumpCardFixture().forEach(player::addCard)
        minCardFixture().forEach(dealer::addCard)

        val result = player.compare(dealer)

        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어와 딜러의 점수가 같으면 무승부이다`() {
        trumpCardFixture().forEach(player::addCard)
        trumpCardFixture().forEach(dealer::addCard)

        val result = player.compare(dealer)

        assertEquals(GameResult.PUSH, result)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 작으면 딜러가 승리한다`() {
        minCardFixture().forEach(player::addCard)
        trumpCardFixture().forEach(dealer::addCard)

        val result = player.compare(dealer)

        assertEquals(GameResult.LOSE, result)
    }
}
