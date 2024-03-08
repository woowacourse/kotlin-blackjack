package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun Hand(vararg cards: Card): Hand {
    return Hand(cards.toList())
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
}

class StateTest {
    @Test
    fun `카드의 숫자합이 기준점을 넘지 않으면 초기 카드 상태는 Hit이다`() {
        val threshold = 21
        val state = State.initializeSetting(Hand(Card(10), Card(9)), threshold)
        assertThat(state is Hit).isTrue()
    }

    @Test
    fun `카드의 숫자합이 기준점이면 상태는 Blackjack이다`() {
        val threshold = 21
        val state = State.initializeSetting(Hand(Card(10), Card(11)), threshold)
        assertThat(state is Blackjack).isTrue()
    }

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
