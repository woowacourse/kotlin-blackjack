package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드의 합이 기준점 미만인 경우에만 추가로 뽑을 수 있다`() {
        val player =
            Player("레오", cards = mutableListOf(Card(CardNumber.THREE, CardShape.DIAMOND), Card(CardNumber.EIGHT, CardShape.DIAMOND)))
        val threshold = 21
        assertThat(player.canPickCard(threshold)).isTrue()
    }

    @Test
    fun `카드의 합이 기준점 이상이라면 더 이상 카드를 뽑을 수 없다`() {
        val player =
            Player("레오", cards = mutableListOf(Card(CardNumber.NINE, CardShape.DIAMOND), Card(CardNumber.EIGHT, CardShape.DIAMOND)))
        val threshold = 21
        assertThat(player.canPickCard(threshold)).isFalse()
    }
}
