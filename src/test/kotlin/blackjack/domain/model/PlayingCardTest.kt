package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {
    // given
    private val cards: ArrayDeque<Card> = ArrayDeque(listOf(Card(Shape.Diamond, CardNumber.Ace)))
    private val deck: PlayingCard = PlayingCard(cards)

    @Test
    fun `카드를 첫번째 장에 있는 카드를 나누어준다`() {
        // when
        val actual = deck.spreadCard()
        val expected = Card(Shape.Diamond, CardNumber.Ace)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
