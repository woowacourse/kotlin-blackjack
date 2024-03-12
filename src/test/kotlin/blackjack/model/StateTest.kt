package blackjack.model

import blackjack.model.TestUtils.Card
import blackjack.model.TestUtils.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StateTest {
    @Test
    fun `Hit 상태에서 카드를 뽑았을 때 기준점을 초과하면 Bust를 반환한다`() {
        val result = Hit(Hand(Card(8), Card(9))).draw(Card(5))
        assertThat(result is Bust).isTrue()
    }

    @Test
    fun `Hit 상태에서 카드를 뽑았을 때 기준점을 미만이라면 Hit를 반환한다`() {
        val result = Hit(Hand(Card(8), Card(4))).draw(Card(5))
        assertThat(result is Hit).isTrue()
    }

    @Test
    fun `Hit 상태에서 카드를 뽑았을 때 기준점이면 Stay를 반환한다`() {
        val result = Hit(Hand(Card(9), Card(7))).draw(Card(5))
        assertThat(result is Stay).isTrue()
    }

    @Test
    fun `Hit 상태에서 사용자가 카드 뽑기를 거부할 경우 Stay를 반환한다`() {
        val result = Hit(Hand(Card(9), Card(7))).stay()
        assertThat(result is Stay).isTrue()
    }
}
