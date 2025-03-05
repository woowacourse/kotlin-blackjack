package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Player
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun `게임을 시작하면 각 플레이어와 딜러는 2장의 카드를 지급받는다`() {
        val player = listOf(Player("peto"), Player("bibi"))
        val dealer = Dealer()
        BlackJackGame(player, dealer).initializedHandOutCards(2)
    }
}
