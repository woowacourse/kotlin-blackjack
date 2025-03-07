package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VerdictTest {
    @Test
    fun `플레이어의 점수가 딜러보다 높을 시 플레이어가 승리한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.KING))) // 21점
        val dealer = Dealer("딜러", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        assertThat(Verdict.determine(dealer, player)).isEqualTo(Verdict.WIN)
    }

    @Test
    fun `플레이어는 버스트되지 않고 딜러는 버스트됐을 시 플레이어가 승리한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.KING))) // 21점
        val dealer = Dealer("딜러", listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        assertThat(Verdict.determine(dealer, player)).isEqualTo(Verdict.WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 낮을 시 플레이어가 패배한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        val dealer = Dealer("딜러", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.KING))) // 21점
        assertThat(Verdict.determine(dealer, player)).isEqualTo(Verdict.LOSE)
    }

    @Test
    fun `플레이어는 버스트되고 딜러는 버스트되지 않았을 시 플레이어가 패배한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        val dealer = Dealer("딜러", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        assertThat(Verdict.determine(dealer, player)).isEqualTo(Verdict.LOSE)
    }

    @Test
    fun `플레이어와 딜러가 모두 버스트됐을 시 플레이어가 패배한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        val dealer = Dealer("딜러", listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        assertThat(Verdict.determine(dealer, player)).isEqualTo(Verdict.LOSE)
    }

    @Test
    fun `플레이어와 딜러가 비긴다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        val dealer = Dealer("딜러", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        assertThat(Verdict.determine(dealer, player)).isEqualTo(Verdict.DRAW)
    }
}
