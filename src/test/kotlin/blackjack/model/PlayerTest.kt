package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 갖는다`() {
        val player = Player(Nickname("호두"))
        val cards = Card.of(listOf(Pair(Pattern.HEART, CardNumber.ACE)))
        player.addCard(cards.first())

        Assertions.assertThat(player.nickname.name).isEqualTo("호두")
    }

    @Test
    fun `플레이어는 뽑은 카드를 가진다`() {
        val player = Player(Nickname("호두"))
        val cards = Card.of(listOf(Pair(Pattern.HEART, CardNumber.ACE)))

        player.addCard(cards.first())

        Assertions.assertThat(player.hand.cards.last()).isEqualTo(cards.first())
    }
}
