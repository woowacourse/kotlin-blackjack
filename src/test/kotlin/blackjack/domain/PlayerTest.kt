package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 갖는다`() {
        val player = Player("pobi")
        assertThat(player.name).isEqualTo("pobi")
    }

    @Test
    fun `플레이어는 카드 목록에 카드를 추가한다`() {
        val player = Player("pobi")
        player.addCard(Card.of(2))

        assertThat(player.hand.cards).containsExactly(Card.of(2))
    }
}
