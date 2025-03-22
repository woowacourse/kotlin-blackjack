package blackjack.domain.state

import blackjack.domain.ClubAce
import blackjack.domain.ClubEight
import blackjack.domain.ClubFive
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

    @Test
    fun `stay일 때 상대가 블랙잭이면 수익률은 -1이다`() {
        val dealer = Dealer()
        dealer.state.hand.addCard(ClubAce)
        dealer.state.hand.addCard(ClubKing)
        val player = Player("name1", Money(1000))
        player.state.hand.addCard(ClubAce)
        player.state.hand.addCard(ClubFive)
        val result = Stay(player.state.hand).profit(Blackjack(dealer.state.hand))
        assertThat(result).isEqualTo(-1.0)
    }

    @Test
    fun `stay일 때 상대가 bust 수익률은 1이다`() {
        val dealer = Dealer()
        dealer.state.hand.addCard(ClubAce)
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubKing)
        val player = Player("name1", Money(1000))
        player.state.hand.addCard(ClubAce)
        player.state.hand.addCard(ClubKing)
        val result = Stay(player.state.hand).profit(Bust(dealer.state.hand))
        assertThat(result).isEqualTo(1.0)
    }

    @Test
    fun `stay일 때 상대가 stay이고 상대보다 점수가 높으면 수익률은 1이다`() {
        val dealer = Dealer()
        dealer.state.hand.addCard(ClubEight)
        dealer.state.hand.addCard(ClubKing)
        val player = Player("name1", Money(1000))
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubKing)
        val result = Stay(player.state.hand).profit(Stay(dealer.state.hand))
        assertThat(result).isEqualTo(1.0)
    }

    @Test
    fun `stay일 때 상대가 stay이고 상대와 점수가 같으면 수익률은 0이다`() {
        val dealer = Dealer()
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubKing)
        val player = Player("name1", Money(1000))
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubKing)
        val result = Stay(player.state.hand).profit(Stay(dealer.state.hand))
        assertThat(result).isEqualTo(0.0)
    }

    @Test
    fun `stay일 때 상대가 stay이고 상대보다 점수가 낮면 수익률은 -1이다`() {
        val dealer = Dealer()
        dealer.state.hand.addCard(ClubEight)
        dealer.state.hand.addCard(ClubAce)
        val player = Player("name1", Money(1000))
        player.state.hand.addCard(ClubAce)
        player.state.hand.addCard(ClubAce)
        val result = Stay(player.state.hand).profit(Stay(dealer.state.hand))
        assertThat(result).isEqualTo(-1.0)
    }
}
