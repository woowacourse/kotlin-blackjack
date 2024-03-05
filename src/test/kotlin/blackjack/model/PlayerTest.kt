package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 뽑은 카드를 가진다`() {
        val player = Player()
        val card = Card(pattern = Pattern.HEART, number = CardNumber.ACE)

        player.takeCard(card)

        Assertions.assertThat(player.cards.last()).isEqualTo(card)
    }

    @Test
    fun `플레이어의 카드를 보여준다`() {
        val player = Player()
        val card = Card(pattern = Pattern.HEART, number = CardNumber.ACE)

        player.takeCard(card)

        Assertions.assertThat(player.cards).isEqualTo(listOf(card))
    }
}
