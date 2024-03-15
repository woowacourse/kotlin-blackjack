package blackjack.model.card.state

import blackjack.model.card.CardDeck
import blackjack.model.card.CardHand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitialStateTest {
    @Test
    fun `첫 턴에서 카드를 드로우하면 랜덤으로 카드 한 장을 뽑아서 InitialState 가 된다`() {
        val initialState = InitialState(CardHand())
        val actualCardsState = initialState.draw(CardDeck.getRandomCard())

        assertThat(actualCardsState is InitialState).isTrue
        assertThat(actualCardsState.countCards()).isEqualTo(1)
    }

    @Test
    fun `첫 턴에서 카드를 한장 뽑은 상태에서 카드를 드로우하면 랜덤으로 카드 한 장을 더 뽑아서 Hit 가 된다`() {
        val initialState = InitialState(CardHand()).draw(CardDeck.getRandomCard())
        val actualCardsState = initialState.draw(CardDeck.getRandomCard())

        assertThat(actualCardsState is Hit).isTrue
        assertThat(actualCardsState.countCards()).isEqualTo(2)
    }
}
