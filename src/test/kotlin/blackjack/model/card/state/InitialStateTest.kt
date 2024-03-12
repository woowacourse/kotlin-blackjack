package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitialStateTest {
    @Test
    fun `첫 턴에 랜덤으로 카드를 한장 뽑으면 여전히 InitialState 가 된다`() {
        val initialState = InitialState(CardHand())
        val actualCardsState = initialState.draw(CardDeck.getRandomCard())

        assertThat(actualCardsState is InitialState)
        assertThat(actualCardsState.countCards()).isEqualTo(1)
    }

    @Test
    fun `첫 턴에 랜덤으로 카드를 두 장 뽑은 상태는 Hit 상태가 된다`() {
        val initialState = InitialState(CardHand())
        val actualCardsState =
            initialState
                .draw(CardDeck.getRandomCard())
                .draw(CardDeck.getRandomCard())

        assertThat(actualCardsState is Hit)
        assertThat(actualCardsState.countCards()).isEqualTo(2)
    }

    @Test
    fun `첫 턴에 명시적인 카드를 한 장 뽑은 결과를 테스트`() {
        val initialState = InitialState(CardHand())
        val actualCardsState = initialState.draw(Card(CardShape.CLOVER, CardNumber.NINE))

        assertThat(actualCardsState.getCardHands()).isEqualTo(CardHand(Card(CardShape.CLOVER, CardNumber.NINE)))
    }

    @Test
    fun `첫 턴에 명시적인 카드를 두 장 뽑은 결과를 테스트`() {
        val initialState = InitialState(CardHand())
        val actualCardsState =
            initialState
                .draw(Card(CardShape.CLOVER, CardNumber.NINE))
                .draw(Card(CardShape.DIAMOND, CardNumber.EIGHT))

        assertThat(actualCardsState.getCardHands()).isEqualTo(
            CardHand(
                Card(CardShape.CLOVER, CardNumber.NINE),
                Card(CardShape.DIAMOND, CardNumber.EIGHT),
            ),
        )
    }
}
