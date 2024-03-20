package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import blackjack.model.result.Money
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BustTest {
    private val bust =
        Bust(
            CardHand(
                Card(CardShape.SPADE, CardNumber.FIVE),
                Card(CardShape.HEART, CardNumber.JACK),
                Card(CardShape.CLOVER, CardNumber.EIGHT),
            ),
        )

    @DisplayName("플레이어: 버스트,  딜러: 버스트 이면 플레이어는 -1.0 배의 손해를 입는다")
    @Test
    fun `플레이어와 딜러가 둘 다 버스트`() {
        val other: CardHandState =
            Bust(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.CLOVER, CardNumber.JACK),
                    Card(CardShape.DIAMOND, CardNumber.THREE),
                    Card(CardShape.HEART, CardNumber.NINE),
                ),
            )
        val actualProfit = bust.calculateProfit(Money(1_000), other)
        Assertions.assertThat(actualProfit).isEqualTo(Money(-1_000))
    }

    @DisplayName("플레이어: 버스트,  딜러: 스테이 이면 플레이어는 -1.0 배의 손해를 입는다")
    @Test
    fun `플레이어는 버스트이고 딜러가 스테이`() {
        val other: CardHandState =
            Stay(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.THREE),
                    Card(CardShape.HEART, CardNumber.NINE),
                ),
            )
        val actualProfit = bust.calculateProfit(Money(1_000), other)
        Assertions.assertThat(actualProfit).isEqualTo(Money(-1_000))
    }

    @DisplayName("플레이어: 버스트,  딜러: 블랙잭 이면 플레이어는 -1.0 배의 손해를 입는다")
    @Test
    fun `플레이어는 버스트이고 딜러가 블랙잭`() {
        val other: CardHandState =
            BlackJack(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.ACE),
                    Card(CardShape.HEART, CardNumber.QUEEN),
                ),
            )
        val actualProfit = bust.calculateProfit(Money(1_000), other)
        Assertions.assertThat(actualProfit).isEqualTo(Money(-1_000))
    }
}
