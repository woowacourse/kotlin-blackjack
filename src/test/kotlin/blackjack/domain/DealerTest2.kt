package blackjack.domain

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer2
import blackjack.fixture.bustTrumpCardFixture
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest2 {
    private lateinit var dealer: Dealer2

    @BeforeEach
    fun setUp() {
        dealer = Dealer2()
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
    fun `딜러가 에이스 카드가 없으면 에이스 카드가 없음을 반환한다`() {
        val expected = dealer.hasAce()

        assertEquals(expected, false)
    }

    @Test
    fun `딜러가 에이스 카드가 있으면 에이스 카드가 있음을 반환한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))

        val expected = dealer.hasAce()

        assertEquals(expected, true)
    }

    @Test
    fun `딜러 카드의 카드를 추가하면 사이즈가 증가한다`() {
        dealer.addCard(TrumpCard(Tier.KING, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.TEN, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.TEN, Shape.HEART))

        assertEquals(dealer.cardSize(), 3)
    }

    @Test
    fun `딜러 카드의 총합이 21을 초과하면 버스트 된다`() {
        bustTrumpCardFixture().forEach {
            dealer.addCard(it)
        }

        assertEquals(dealer.isBust(), true)
    }

    @Test
    fun `에이스가 없을 때 총합을 구해서 16을 초과하면 최대 점수를 초과 했음을 반환한다`() {
        bustTrumpCardFixture().forEach {
            dealer.addCard(it)
        }

        assertEquals(dealer.isOverMaxScore(), true)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되어도 버스트 되지 않고 총합이 16을 초과하면 최대 점수를 초과 했음을 반환한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.KING, Shape.DIA))

        assertEquals(dealer.isOverMaxScore(), true)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되어도 버스트 되지 않고 총합이 16을 초과하지 않으면 최대 점수를 초과하지 않았음을 반환한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.TWO, Shape.DIA))

        assertEquals(dealer.isOverMaxScore(), false)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되면 버스트 되고 에이스를 1로 계산했을 때 총합이 16을 초과하면 최대 점수를 초과 했음을 반환한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.KING, Shape.DIA))

        assertEquals(dealer.isOverMaxScore(), true)
    }

    @Test
    fun `에이스가 두 개일 때 버스트 되지 않고 16을 초과하지 않으면 최대 점수를 초과 하지 않았음을 반환한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.ACE, Shape.HEART))

        assertEquals(dealer.isOverMaxScore(), false)
    }

    @Test
    fun `에이스가 여러 개일 때 버스트 되지 않고 16을 초과하면 최대 점수를 초과 했음을 반환한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.ACE, Shape.HEART))
        dealer.addCard(TrumpCard(Tier.NINE, Shape.HEART))

        assertEquals(dealer.isOverMaxScore(), true)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되지 않았으면 카드 총합에 10을 더한다`() {
        dealer.addCard(TrumpCard(Tier.ACE, Shape.DIA))
        dealer.addCard(TrumpCard(Tier.NINE, Shape.HEART))

        assertEquals(dealer.totalScore(), 20)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되었으면 카드 총합을 유지한다`() {
        bustTrumpCardFixture().forEach {
            dealer.addCard(it)
        }

        assertEquals(dealer.totalScore(), 30)
    }
}
