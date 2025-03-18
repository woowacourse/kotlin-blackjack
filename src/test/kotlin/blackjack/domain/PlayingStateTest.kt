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
    fun `Ready에서 draw 하면 Hit으로 전환`() {
        val hand = Hand(listOf())
        val state = Ready(hand).draw(ClubSix)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때, bust면 Bust로 전환`() {
        val hand = Hand(listOf(ClubNine, ClubEight))
        val state = Hit(hand).draw(ClubSeven)
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때, blackjack면 Blackjack로 전환`() {
        val hand = Hand(listOf(ClubAce))
        val state = Hit(hand).draw(ClubTen)
        assertThat(state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때 bust나 blackjack이 아니면 Hit으로 전환`() {
        val hand = Hand(listOf(ClubFour))
        val state = Hit(hand).draw(ClubFive)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit에서 stay 했을 때 stay로 전환`() {
        val hand = Hand(listOf(ClubQueen, ClubKing))
        val state = Hit(hand).stay()
        assertThat(state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `딜러가 blackjack이고 플레이어가 blackjack이면 무승부다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.state = Blackjack(dealer.state.hand)
        val result = Blackjack(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.PUSH)
    }

    @Test
    fun `딜러가 blackjack이고 플레이어가 stay이면 플레이어가 진다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.state = Blackjack(dealer.state.hand)
        val result = Stay(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 blackjack이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubAce)
        val result = Bust(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 bust이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = Dealer()
        val player = Player("name1")
        val result = Bust(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 bust이고 플레이어가 blackjack이면 플레이어가 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1")
        val result = Blackjack(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 bust이고 플레이어가 stay이면 플레이어가 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.state = Bust(dealer.state.hand)
        val result = Stay(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 Stay이고 플레이어가 blackjack이면 플레이어가 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1")
        val result = Blackjack(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 Stay이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = Dealer
        val player = Player
        val result = Bust(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러와 플레이어가 stay일 때 플레이어 점수가 더 크면 플레이어가 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubEight)
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubQueen)
        val result = Stay(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러와 플레이어가 stay일 때 플레이어 점수가 같으면 플레이어가 비긴다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubEight)
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubEight)
        val result = Stay(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.PUSH)
    }

    @Test
    fun `딜러와 플레이어가 stay일 때 플레이어 점수가 더 작으면 진다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.state.hand.addCard(ClubKing)
        dealer.state.hand.addCard(ClubQueen)
        player.state.hand.addCard(ClubKing)
        player.state.hand.addCard(ClubEight)
        val result = Stay(player.state.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }
}
