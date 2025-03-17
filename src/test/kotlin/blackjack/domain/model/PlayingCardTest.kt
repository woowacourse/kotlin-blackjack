package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardFactory
import blackjack.domain.model.card.PlayingCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayingCardTest {
    // given
    private val cards: ArrayDeque<Card> = CardFactory().makeCard()
    private val deck: PlayingCard = PlayingCard(cards)

    @ValueSource(ints = [1, 10, 20])
    @ParameterizedTest
    fun `필요한 개수만큼 카드를 반환한다`(input: Int) {
        // when
        val actual = deck.spreadCard(input).size
        val expected = input
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
