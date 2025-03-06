package blackjack

import blackjack.model.Dealer
import blackjack.model.GameManager
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `초기 세팅은 플레이어와 딜러에게 카드 2장씩 나눠준다`() {
        // given
        val player1 = Player("a")
        val player2 = Player("b")
        val dealer = Dealer()
        val gameManager = GameManager(dealer, listOf(player1, player2))

        // when
        gameManager.dealInitialCardWithCount(2)

        // then
        assertThat(player1.cards.size).isEqualTo(2)
        assertThat(dealer.cards.size).isEqualTo(2)
    }
}
