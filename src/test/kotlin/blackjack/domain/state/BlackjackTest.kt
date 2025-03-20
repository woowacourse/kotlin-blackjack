package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    @Test
    fun `blackjack 상태이고 상대가 blackjack 상태면 무승부다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state = Blackjack(dealer.state.hand)
        val result = Blackjack(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.PUSH)
    }

    @Test
    fun `blackjack 상태일 때 상대가 bust 상태면 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        val result = Blackjack(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `blackjack 상태일 때 상대가 Stay 상태면 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        val result = Blackjack(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.WIN)
    }
}