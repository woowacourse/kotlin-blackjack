package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Ready
import blackjack.domain.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingStateTest {
    @Test
    fun `딜러가 blackjack이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubAce)
        val result = Bust(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 bust이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        val result = Bust(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

//    @Test
//    fun `딜러가 Stay이고 플레이어가 bust이면 플레이어가 진다`() {
//        val dealer = Dealer
//        val player = Player
//        val result = Bust(player.state.hand).decideResult(dealer)
//        assertThat(result).isEqualTo(Result.LOSE)
//    }
}
