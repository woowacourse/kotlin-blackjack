package domain.gamer.cards

import domain.SpadeCardsOf
import domain.card.CardValue.ACE
import domain.card.CardValue.FIVE
import domain.card.CardValue.JACK
import domain.card.CardValue.QUEEN
import domain.card.CardValue.THREE
import domain.card.CardValue.TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 값의 합을 반환한다`() {
        val cards =
            SpadeCardsOf(JACK, QUEEN)

        assertThat(cards.calculateCardSum()).isEqualTo(20)
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘었을 경우 버스트가 난다`() {
        val cards = SpadeCardsOf(JACK, QUEEN, THREE)
        assertThat(cards.isBurst()).isTrue
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘지 않았을 경우 false를 반환한다`() {
        val cards = SpadeCardsOf(JACK, QUEEN)
        assertThat(cards.isBurst()).isFalse
    }

    @Test
    fun `A가 2개 포함되어 있는 카드 값의 합을 반환한다`() {
        val cards = SpadeCardsOf(ACE, ACE)
        assertThat(cards.calculateCardSum()).isEqualTo(12)
    }

    @Test
    fun `A가 하나 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val cards = SpadeCardsOf(ACE, QUEEN, THREE)
        assertThat(cards.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `A가 두개 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val cards = SpadeCardsOf(JACK, TWO, ACE, ACE)
        assertThat(cards.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `카드가 2개이고 합이 21이면 블랙잭이다`() {
        val cards = SpadeCardsOf(JACK, ACE)
        assertThat(cards.checkBlackjack()).isEqualTo(true)
    }

    @Test
    fun `카드가 3개이고 합이 21이면 블랙잭이 아니다`() {
        val cards = SpadeCardsOf(JACK, FIVE, ACE)
        assertThat(cards.checkBlackjack()).isEqualTo(false)
    }

    @Test
    fun `카드가 2개이고 합이 21이 아니면 블랙잭이 아니다`() {
        val cards = SpadeCardsOf(JACK, FIVE)
        assertThat(cards.checkBlackjack()).isEqualTo(false)
    }
}
