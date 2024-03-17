package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlackJackTest {
    private val blackJack =
        BlackJack(CardHand(Card(CardShape.DIAMOND, CardNumber.ACE), Card(CardShape.CLOVER, CardNumber.KING)))

    @DisplayName("플레이어: 블랙잭,  딜러: 블랙잭 이면 플레이어는 0 배의 수익을 받는다")
    @Test
    fun `플레이어와 딜러가 둘 다 블랙잭`() {
        val other: CardHandState =
            BlackJack(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.HEART, CardNumber.JACK),
                ),
            )
        val actualProfit = blackJack.calculateProfit(1_000, other)
        assertThat(actualProfit).isEqualTo(0.0)
    }

    @DisplayName("플레이어: 블랙잭,  딜러: stay 이면 플레이어는 1.5 배의 수익을 받는다")
    @Test
    fun `플레이어는 블랙잭이고 딜러는 stay`() {
        val other: CardHandState =
            Stay(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.HEART, CardNumber.FIVE),
                ),
            )
        val actualProfit = blackJack.calculateProfit(1_000, other)
        assertThat(actualProfit).isEqualTo(1_500.0)
    }

    @DisplayName("플레이어: 블랙잭,  딜러: Bust 이면 플레이어는 1.5 배의 수익을 받는다")
    @Test
    fun `플레이어는 블랙잭이고 딜러는 Bust`() {
        val other: CardHandState =
            Bust(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.THREE),
                    Card(CardShape.HEART, CardNumber.JACK),
                    Card(CardShape.HEART, CardNumber.KING),
                ),
            )
        val actualProfit = blackJack.calculateProfit(1_000, other)
        assertThat(actualProfit).isEqualTo(1_500.0)
    }
}
