package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MultiDeckTest {

    @Test
    fun `카드 모양과 숫자의 조합에 일치하는 하나의 무작위 카드를 뽑는다`() {
        val multiDeck = MultiDeck()
        val cards =
            CardNumber.values().flatMap { number -> CardShape.values().map { shape -> Card.from(number, shape) } }
        assertThat(cards).contains(multiDeck.draw())
    }

    @Test
    fun `카드 덱에 'A 하트' 다음에 '3 스페이드'가 있을 때, 1장을 뽑으면 'A 하트'가 나온다`() {
        val multiDeck = MultiDeck(
            Pair(CardNumber.ONE, CardShape.HEART),
            Pair(CardNumber.THREE, CardShape.SPADE)
        )

        val actual = multiDeck.draw()

        assertThat(actual).isEqualTo(Card.from(CardNumber.ONE, CardShape.HEART))
    }
}
