package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 이름을 가진다`() {
        val dealer = Dealer()

        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러는 카드를 받아서 추가할 수 있다`() {
        val dealer = Dealer()

        dealer.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )

        assertThat(dealer.state.getHandCards()).isEqualTo(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )
    }

    @Test
    fun `딜러는 카드 패의 한장만 보여줄 수 있다`() {
        val dealer = Dealer()

        dealer.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )

        assertThat(dealer.showOneCard()).isEqualTo(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
            ),
        )
    }
}
