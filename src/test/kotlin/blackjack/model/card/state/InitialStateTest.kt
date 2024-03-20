package blackjack.model.card.state

import blackjack.model.card.CardHand
import blackjack.model.card.RandomCardDrawStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitialStateTest {
    private val cardDrawStrategy = RandomCardDrawStrategy

    @Test
    fun `첫 턴에서 카드를 드로우하면 랜덤으로 카드 한 장을 뽑아서 InitialState 가 된다`() {
        val initialState = InitialState(CardHand())
        val actualCardsState = initialState.draw(cardDrawStrategy.drawCard())

        assertThat(actualCardsState is InitialState).isTrue
        assertThat(actualCardsState.countCards()).isEqualTo(1)
    }

    @Test
    fun `첫 턴에서 카드를 한장 뽑은 상태에서 카드를 드로우하면 랜덤으로 카드 한 장을 더 뽑아서 Hit 혹은 BlackJack 가 된다`() {
        val initialState = InitialState(CardHand()).draw(cardDrawStrategy.drawCard())
        val actualCardsState = initialState.draw(cardDrawStrategy.drawCard())

        assertThat(actualCardsState is Hit || actualCardsState is BlackJack).isTrue
        assertThat(actualCardsState.countCards()).isEqualTo(2)
    }
}
