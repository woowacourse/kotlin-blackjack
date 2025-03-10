package blackjack

import blackjack.domain.ParticipantCards
import blackjack.domain.card.CardTier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 게임을 시작하면 카드를 2장 지급받는다`() {
        val dealer = Dealer(ParticipantCards())
        val fixture = trumpCardFixture()
        fixture.forEach {
            dealer.receiveCard(it)
        }
        assertThat(dealer.getAllCards()).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `딜러 카드의 총합이 21을 초과하면 버스트 된다`() {
        val dealer = Dealer(ParticipantCards())
        repeat(3) {
            dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(dealer.isBust()).isEqualTo(true)
    }

    @Test
    fun `에이스가 없을 때 총합을 구해서 16을 초과하면 true를 반환한다`() {
        val dealer = Dealer(ParticipantCards())

        dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.KING, Shape.HEART))

        val expected = true
        assertThat(dealer.isOverMaxScore()).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되어도 버스트 되지 않고 총합이 16을 초과하면 true를 반환한다`() {
        val dealer = Dealer(ParticipantCards())

        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))

        val expected = true
        assertThat(dealer.isOverMaxScore()).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되어도 버스트 되지 않고 총합이 16을 초과하지 않으면 false를 반환한다`() {
        val dealer = Dealer(ParticipantCards())

        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.TWO, Shape.DIA))

        val expected = false
        assertThat(dealer.isOverMaxScore()).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있을 때 에이스가 11로 계산 되면 버스트 되고 에이스를 1로 계산했을 때 총합이 16을 초과하면 true를 반환한다`() {
        val dealer = Dealer(ParticipantCards())

        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))

        val expected = true
        assertThat(dealer.isOverMaxScore()).isEqualTo(expected)
    }

    @Test
    fun `에이스가 두 개일 때 버스트 되지 않고 16을 초과하지 않으면 false를 반환한다`() {
        val dealer = Dealer(ParticipantCards())

        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.HEART))

        val expected = false
        assertThat(dealer.isOverMaxScore()).isEqualTo(expected)
    }

    @Test
    fun `에이스가 여러 개일 때 버스트 되지 않고 16을 초과하면 true를 반환한다`() {
        val dealer = Dealer(ParticipantCards())

        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.HEART))
        dealer.receiveCard(TrumpCard(CardTier.NINE, Shape.HEART))

        val expected = true
        assertThat(dealer.isOverMaxScore()).isEqualTo(expected)
    }
}
