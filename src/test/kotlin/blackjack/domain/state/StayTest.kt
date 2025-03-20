package blackjack.domain.state

import blackjack.domain.ClubEight
import blackjack.domain.ClubKing
import blackjack.domain.ClubQueen
import blackjack.domain.Dealer
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StayTest {
    @Test
    fun `stay 상태일 때 상대 점수가 더 작으면 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubEight)
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubQueen)
        val result = Stay(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `stay 상태일 때 상대와 점수가 같으면 비긴다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubEight)
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubEight)
        val result = Stay(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.PUSH)
    }

    @Test
    fun `stay 상태일 때 상대 점수가 더 크면 진다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubQueen)
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubEight)
        val result = Stay(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `stay 상태에서 상대가 blackjack이면 진다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state = Blackjack(dealer.state.hand)
        val result = Stay(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `stay 상태에서 상대가 bust이면 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1", Money(1000))
        dealer.state = Bust(dealer.state.hand)
        val result = Stay(player.state.hand).decideResult(dealer.state)
        assertThat(result).isEqualTo(Result.WIN)
    }
}