package blackjack.domain.card

import blackjack.domain.card.CardMark.CLOVER
import blackjack.domain.card.CardValue.EIGHT
import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_EIGHT
import blackjack.domain.state.Fixtures.CLOVER_SEVEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        var cards = Cards()
        cards += CLOVER_EIGHT
        assertAll(
            { assertThat(cards.size).isEqualTo(1) },
            { assertThat(cards.toList()[0]).isEqualTo(Card(CLOVER, EIGHT)) },
        )
    }

    @Test
    fun `ACE 카드가 없으면 false를 반환한다`() {
        assertThat(Cards(CLOVER_EIGHT).isContainsAce).isFalse
    }

    @Test
    fun `ACE 카드가 있으면 true를 반환한다`() {
        assertThat(Cards(CLOVER_ACE).isContainsAce).isTrue
    }

    @Test
    fun `점수의 합을 반환한다`() {
        val cards = Cards(CLOVER_EIGHT, CLOVER_SEVEN)
        assertThat(cards.calculateScore().value).isEqualTo(15)
    }

    @Test
    fun `들어오는 카드가 이미 있으면 에러를 낸다`() {
        var cards = Cards(CLOVER_EIGHT)
        assertThrows<IllegalArgumentException> { cards += CLOVER_EIGHT }
    }

    @Test
    fun `모든 경우의 수를 반환한다`() {
        assertThat(Cards.all().size).isEqualTo(52)
    }
}
