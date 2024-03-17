package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StayTest {
    private val bust =
        Stay(
            CardHand(
                Card(CardShape.HEART, CardNumber.JACK),
                Card(CardShape.CLOVER, CardNumber.SEVEN),
            ),
        )

    @DisplayName("플레이어: 스테이,  딜러: 블랙잭 이면 플레이어는 -1.0 배의 손해를 입는다")
    @Test
    fun `플레이어는 스테이이고 딜러는 블랙잭`() {
        val other: CardHandState =
            BlackJack(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.CLOVER, CardNumber.JACK),
                ),
            )
        val actualProfit = bust.calculateProfit(1_000, other)
        Assertions.assertThat(actualProfit).isEqualTo(-1_000.0)
    }

    @DisplayName("플레이어: 스테이,  딜러: 버스트 이면 플레이어는 1.0 배의 수익을 얻는다")
    @Test
    fun `플레이어는 스테이이고 딜러가 버스트`() {
        val other: CardHandState =
            Bust(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.THREE),
                    Card(CardShape.HEART, CardNumber.NINE),
                    Card(CardShape.SPADE, CardNumber.JACK),
                ),
            )
        val actualProfit = bust.calculateProfit(1_000, other)
        Assertions.assertThat(actualProfit).isEqualTo(1_000.0)
    }

    @DisplayName("플레이어: 스테이,  딜러: 스테이, 플레이어의 점수가 더 높으면 플레이어는 1.0 배의 수익을 얻는다")
    @Test
    fun `플레이어와 딜러 둘 다 스테이, 플레이어 점수가 더 높음`() {
        val other: CardHandState =
            Stay(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.SIX),
                    Card(CardShape.HEART, CardNumber.QUEEN),
                ),
            )
        val actualProfit = bust.calculateProfit(1_000, other)
        Assertions.assertThat(actualProfit).isEqualTo(1_000.0)
    }

    @DisplayName("플레이어: 스테이,  딜러: 스테이, 플레이어의 점수가 더 낮으면 플레이어는 -1.0 배의 손해을 입는다")
    @Test
    fun `플레이어와 딜러 둘 다 스테이, 플레이어 점수가 더 낮음`() {
        val other: CardHandState =
            Stay(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.EIGHT),
                    Card(CardShape.HEART, CardNumber.QUEEN),
                ),
            )
        val actualProfit = bust.calculateProfit(1_000, other)
        Assertions.assertThat(actualProfit).isEqualTo(-1_000.0)
    }

    @DisplayName("플레이어: 스테이,  딜러: 스테이, 점수가 서로 같으면 플레이어는 0 배의 수익을 얻는다")
    @Test
    fun `플레이어와 딜러 둘 다 스테이, 점수가 서로 같음`() {
        val other: CardHandState =
            Stay(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.SEVEN),
                    Card(CardShape.HEART, CardNumber.QUEEN),
                ),
            )
        val actualProfit = bust.calculateProfit(1_000, other)
        Assertions.assertThat(actualProfit).isEqualTo(0.0)
    }
}
