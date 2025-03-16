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
        val state = Ready(hand).draw(TestFixture.ClubSix)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때, bust면 Bust로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubNine, TestFixture.ClubEight))
        val state = Hit(hand).draw(TestFixture.ClubSeven)
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때, blackjack면 Blackjack로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubAce))
        val state = Hit(hand).draw(TestFixture.ClubTen)
        assertThat(state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때 bust나 blackjack이 아니면 Hit으로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubFour))
        val state = Hit(hand).draw(TestFixture.ClubFive)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit에서 stay 했을 때 stay로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubQueen, TestFixture.ClubKing))
        val state = Hit(hand).stay()
        assertThat(state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `딜러가 blackjack이고 플레이어가 blackjack이면 무승부다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Blackjack(dealer.hand))
        player.changeState(Blackjack(player.hand))
        val result = Blackjack(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.PUSH)
    }

    @Test
    fun `딜러가 blackjack이고 플레이어가 stay이면 플레이어가 진다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Blackjack(dealer.hand))
        player.changeState(Stay(player.hand))
        val result = Stay(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 blackjack이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Blackjack(dealer.hand))
        player.changeState(Stay(player.hand))
        val result = Bust(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 bust이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Bust(dealer.hand))
        player.changeState(Bust(player.hand))
        val result = Bust(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 bust이고 플레이어가 blackjack이면 플레이어가 이긴다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Bust(dealer.hand))
        player.changeState(Blackjack(player.hand))
        val result = Blackjack(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 bust이고 플레이어가 stay이면 플레이어가 이긴다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Bust(dealer.hand))
        player.changeState(Stay(player.hand))
        val result = Stay(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 Stay이고 플레이어가 blackjack이면 플레이어가 이긴다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Stay(dealer.hand))
        player.changeState(Blackjack(player.hand))
        val result = Blackjack(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 Stay이고 플레이어가 bust이면 플레이어가 진다`() {
        val dealer = TestFixture.Dealer
        val player = TestFixture.Player
        dealer.changeState(Stay(dealer.hand))
        player.changeState(Bust(player.hand))
        val result = Bust(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러와 플레이어가 stay일 때 플레이어 점수가 더 크면 플레이어가 이긴다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.hand.addCard(TestFixture.ClubKing)
        dealer.hand.addCard(TestFixture.ClubEight)
        player.hand.addCard(TestFixture.ClubKing)
        player.hand.addCard(TestFixture.ClubQueen)
        dealer.changeState(Stay(dealer.hand))
        player.changeState(Stay(player.hand))
        assertThat(player.hand.cards.size).isEqualTo(2)
        assertThat(dealer.hand.cards.size).isEqualTo(2)
        val result = Stay(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러와 플레이어가 stay일 때 플레이어 점수가 같으면 플레이어가 비긴다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.hand.addCard(TestFixture.ClubKing)
        dealer.hand.addCard(TestFixture.ClubEight)
        player.hand.addCard(TestFixture.ClubKing)
        player.hand.addCard(TestFixture.ClubEight)
        dealer.changeState(Stay(dealer.hand))
        player.changeState(Stay(player.hand))

        assertThat(player.hand.cards.size).isEqualTo(2)
        assertThat(dealer.hand.cards.size).isEqualTo(2)
        val result = Stay(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.PUSH)
    }

    @Test
    fun `딜러와 플레이어가 stay일 때 플레이어 점수가 더 작으면 진다`() {
        val dealer = Dealer()
        val player = Player("name1")
        dealer.hand.addCard(TestFixture.ClubKing)
        dealer.hand.addCard(TestFixture.ClubQueen)
        player.hand.addCard(TestFixture.ClubKing)
        player.hand.addCard(TestFixture.ClubEight)
        dealer.changeState(Stay(dealer.hand))
        player.changeState(Stay(player.hand))
        val result = Stay(player.hand).decideResult(dealer)
        assertThat(result).isEqualTo(Result.LOSE)
    }
}
