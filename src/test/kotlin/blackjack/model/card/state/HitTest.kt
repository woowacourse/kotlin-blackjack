package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `Hit 상태에서 카드를 뽑아 카드 숫자의 합이 21 초과하면 Bust 가 된다`() {
        val hitState =
            Hit(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                ),
            )
        val actualState = hitState.draw(Card(CardShape.DIAMOND, CardNumber.KING))
        val expectedState =
            Bust(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                    Card(CardShape.DIAMOND, CardNumber.KING),
                ),
            )
        assertThat(actualState is Bust).isTrue
        assertThat(actualState.getCardHands()).isEqualTo(expectedState.getCardHands())
    }

    @Test
    fun `Hit 상태에서 카드를 뽑아 카드 숫자의 합이 21 이하이면 Hit 이 된다`() {
        val hitState =
            Hit(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                ),
            )
        val actualState = hitState.draw(Card(CardShape.DIAMOND, CardNumber.THREE))
        val expectedState =
            Hit(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                    Card(CardShape.DIAMOND, CardNumber.THREE),
                ),
            )
        assertThat(actualState is Hit).isTrue
        assertThat(actualState.getCardHands()).isEqualTo(expectedState.getCardHands())
    }

    @Test
    fun `Hit 상태에서 카드 뽑지 않기를 선언하고 카드 숫자의 합이 21 미만이면 Stay 가 된다`() {
        val hitState =
            Hit(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                ),
            )
        val actualState = hitState.stay()
        val expectedState =
            Stay(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                ),
            )
        assertThat(actualState is Stay).isTrue
        assertThat(actualState.getCardHands()).isEqualTo(expectedState.getCardHands())
    }

    @Test
    fun `Hit 상태에서 카드 뽑지 않기를 선언하고 카드 숫자 합이 21 이면 BlackJack 이 된다`() {
        val hitState =
            Hit(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.ACE),
                ),
            )
        val actualState = hitState.stay()
        val expectedState =
            BlackJack(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.QUEEN),
                    Card(CardShape.HEART, CardNumber.ACE),
                ),
            )

        assertThat(actualState is BlackJack).isTrue
        assertThat(actualState.getCardHands()).isEqualTo(expectedState.getCardHands())
    }
}
