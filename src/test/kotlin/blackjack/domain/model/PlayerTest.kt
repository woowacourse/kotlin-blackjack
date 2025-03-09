package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("A", listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("A")
    }

    @Test
    fun `플레이어는 카드를 가진다`() {
        assertThat(player.showHand()).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val actual = Player("A", listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)))
        player.accept(listOf(Card(Suit.HEART, Rank.KING)))
        assertThat(player.showHand()).isEqualTo(actual.showHand())
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "\t", "\n"])
    fun `플레이어의 이름이 공백일 시 오류가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> { Player(value) }
    }

    @Test
    fun `플레이어가 버스트되지 않았으면 히트할 수 있다`() {
        assertThat(player.canHit()).isTrue()
    }

    @Test
    fun `플레이어가 버스트됐으면 히트할 수 없다`() {
        player.accept(listOf(Card(Suit.HEART, Rank.JACK), Card(Suit.HEART, Rank.QUEEN), Card(Suit.HEART, Rank.KING)))
        assertThat(player.canHit()).isFalse()
    }

    @Test
    fun `플레이어의 점수가 딜러보다 높을 시 플레이어가 승리한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.KING))) // 21점
        val dealer = Dealer(cards = listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        assertThat(player.compareAgainst(dealer)).isEqualTo(Result.WIN)
    }

    @Test
    fun `플레이어는 버스트되지 않고 딜러는 버스트됐을 시 플레이어가 승리한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.KING))) // 21점
        val dealer = Dealer(cards = listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        assertThat(player.compareAgainst(dealer)).isEqualTo(Result.WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 낮을 시 플레이어가 패배한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        val dealer = Dealer(cards = listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.KING))) // 21점
        assertThat(player.compareAgainst(dealer)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어는 버스트되고 딜러는 버스트되지 않았을 시 플레이어가 패배한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        val dealer = Dealer(cards = listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        assertThat(player.compareAgainst(dealer)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어와 딜러가 모두 버스트됐을 시 플레이어가 패배한다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        val dealer = Dealer(cards = listOf(Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING), Card(Suit.CLUB, Rank.KING))) // 30점
        assertThat(player.compareAgainst(dealer)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어와 딜러가 비긴다`() {
        val player = Player("A", listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        val dealer = Dealer(cards = listOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.TWO))) // 13점
        assertThat(player.compareAgainst(dealer)).isEqualTo(Result.DRAW)
    }
}
