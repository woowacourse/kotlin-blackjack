package blackjack.domain.state

import blackjack.domain.ClubAce
import blackjack.domain.ClubKing
import blackjack.domain.Dealer
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BustTest {
    @Test
    fun `플레이어가 bust 상태면 진다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubAce)
        val result = Bust(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `bust일 때 수익률은 -1이다`() {
        val dealer = Dealer()
        dealer.state.hand.addCard(ClubAce)
        dealer.state.hand.addCard(ClubKing)
        val player = Player("name1", Money(1000))
        val result = Bust(player.state.hand).profit(Blackjack(dealer.state.hand))
        assertThat(result).isEqualTo(-1.0)
    }
}