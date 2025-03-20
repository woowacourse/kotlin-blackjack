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
    fun `bust 상태 일 때 상대가 blackjack이면 진다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubAce)
        val result = Bust(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `bust 상태일 때 상대가 bust면 플레이어가 진다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        val result = Bust(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.LOSE)
    }

//    @Test
//    fun `bust 상태일 때 상대가 stay이면 플레이어가 진다`() {
//        val dealer = Dealer
//        val player = Player
//        val result = Bust(player.state.hand).decideResult(dealer)
//        assertThat(result).isEqualTo(Result.LOSE)
//    }
}