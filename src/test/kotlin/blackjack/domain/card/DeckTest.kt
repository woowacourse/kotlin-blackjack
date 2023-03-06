package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `카드 모양과 숫자의 조합에 일치하는 하나의 무작위 카드를 뽑는다`() {
        val deck = Deck()
        val cards =
            CardNumber.values().flatMap { number -> CardShape.values().map { shape -> Card(number, shape) } }
        assertThat(cards).contains(deck.draw())
    }
}
