package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `블랙잭 게임은 초기에 카드 52장을 가진다`() {
        // given
        val cardDeck = CardDeck(RandomCardsGenerator())

        // when
        val actual: Int = cardDeck.getCardsSize()

        // then
        assertThat(actual).isEqualTo(52)
    }

    @Test
    fun `카드를 한 장 발행하면, cardDeck의 카드 개수가 하나 감소한다`() {
        // given
        val cardDeck = CardDeck(RandomCardsGenerator())

        // when
        cardDeck.provide()
        val actual: Int = cardDeck.getCardsSize()

        // then
        assertThat(actual).isEqualTo(51)
    }
}
