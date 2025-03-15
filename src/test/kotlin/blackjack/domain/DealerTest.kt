package blackjack.domain

import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerState
import blackjack.fixture.blackJackCardFixture
import blackjack.fixture.bustTrumpCardFixture
import blackjack.fixture.minCardFixture
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player(PlayerState("", BettingAmount(1)))
    }

    @Test
    fun `딜러가 게임을 시작하면 카드를 2장 지급받는다`() {
        val fixture = trumpCardFixture()
        fixture.forEach {
            dealer.addCard(it)
        }
        assertThat(dealer.cards.items).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `에이스가 없을 때 총합을 구해서 16을 초과하면 카드를 더 뽑을 수 없음을 반환한다`() {
        bustTrumpCardFixture().forEach {
            dealer.addCard(it)
        }

        assertEquals(dealer.canHit(), false)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되어도 버스트 되지 않고 총합이 16을 초과하면 카드를 더 뽑을 수 없음을 반환한다`() {
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Denomination.KING, Shape.DIA))

        assertEquals(dealer.canHit(), false)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되어도 버스트 되지 않고 총합이 16을 초과하지 않으면 카드를 더 뽑을 수 있음을 반환한다`() {
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Denomination.TWO, Shape.DIA))

        assertEquals(dealer.canHit(), true)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되면 버스트 되어 에이스를 1로 계산했을 때 총합이 16을 초과하면 카드를 더 뽑을 수 없음을 반환한다`() {
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Denomination.KING, Shape.DIA))

        assertEquals(dealer.canHit(), false)
    }

    @Test
    fun `에이스가 두 개일 때 버스트 되지 않고 16을 초과하지 않으면 카드를 더 뽑을 수 있음을 반환한다`() {
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.HEART))

        assertEquals(dealer.canHit(), true)
    }

    @Test
    fun `에이스가 여러 개일 때 버스트 되지 않고 16을 초과하면 카드를 더 뽑을 수 없음을 반환한다`() {
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.HEART))
        dealer.addCard(TrumpCard(Denomination.NINE, Shape.HEART))

        assertEquals(dealer.canHit(), false)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되지 않았으면 카드 총합에 10을 더한다`() {
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Denomination.NINE, Shape.HEART))

        assertEquals(dealer.totalScore(), 20)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되었으면 카드 총합을 유지한다`() {
        bustTrumpCardFixture().forEach(dealer::addCard)

        assertEquals(dealer.totalScore(), 30)
    }

    @Test
    fun `최초에 카드를 받은 후 오픈할 카드 1장을 반환한다`() {
        dealer.addCard(TrumpCard(Denomination.SEVEN, Shape.HEART))
        dealer.addCard(TrumpCard(Denomination.ACE, Shape.DIA))

        assertThat(dealer.getInitialCards()).containsExactly(TrumpCard(Denomination.SEVEN, Shape.HEART))
    }

    @Test
    fun `딜러와 플레이어가 모두 블랙잭이면 무승부한다`() {
        blackJackCardFixture().forEach {
            dealer.addCard(it)
            player.addCard(it)
        }

        val result = dealer.compare(player)

        assertEquals(GameResult.PUSH, result)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트 되면 딜러가 승리한다`() {
        bustTrumpCardFixture().forEach(dealer::addCard)
        bustTrumpCardFixture().forEach(player::addCard)

        val result = dealer.compare(player)

        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러가 버스트 되지 않고 플레이어가 버스트 되면 딜러가 승리한다`() {
        minCardFixture().forEach(dealer::addCard)
        bustTrumpCardFixture().forEach(player::addCard)

        val result = dealer.compare(player)

        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 낮으면 딜러가 승리한다`() {
        trumpCardFixture().forEach(dealer::addCard)
        minCardFixture().forEach(player::addCard)

        val result = dealer.compare(player)

        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 높으면 딜러가 패배한다`() {
        minCardFixture().forEach(dealer::addCard)
        trumpCardFixture().forEach(player::addCard)

        val result = dealer.compare(player)

        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `플레이어가 버스트 되면 딜러가 승리한다`() {
        minCardFixture().forEach(dealer::addCard)
        bustTrumpCardFixture().forEach(player::addCard)

        val result = dealer.compare(player)

        assertEquals(GameResult.WIN, result)
    }
}
