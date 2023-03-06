package blackjack.domain.card

import blackjack.domain.card.CardMark.CLOVER
import blackjack.domain.card.CardMark.SPADE
import blackjack.domain.card.CardValue.ACE
import blackjack.domain.card.CardValue.EIGHT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardsTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        var cards = Cards()
        cards += Card(CLOVER, EIGHT)
        assertAll(
            { assertThat(cards.size).isEqualTo(1) },
            { assertThat(cards.toList()[0]).isEqualTo(Card(CLOVER, EIGHT)) },
        )
    }

    @Test
    fun `ACE 카드가 없으면 false를 반환한다`() {
        var cards = Cards()
        cards += Card(CLOVER, EIGHT)
        assertThat(cards.isContainsACE()).isFalse
    }

    @Test
    fun `ACE 카드가 있으면 true를 반환한다`() {
        var cards = Cards()
        cards += Card(CLOVER, ACE)
        assertThat(cards.isContainsACE()).isTrue
    }

    @Test
    fun `점수의 합을 반환한다`() {
        var cards = Cards()
        cards += Card(CLOVER, EIGHT)
        cards += Card(SPADE, EIGHT)
        assertThat(cards.result.score()).isEqualTo(16)
    }
}
