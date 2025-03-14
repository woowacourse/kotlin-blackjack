package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun `setUp`() {
        player = Player("동전", Card(Suit.HEART, Rank.ACE))
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("동전")
    }

    @Test
    fun `플레이어는 입력 받은 숫자 만큼 반환한다 `() {
        assertThat(player.showCards(6)).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val actual =
            listOf(
                Card(Suit.HEART, Rank.ACE),
                Card(Suit.HEART, Rank.KING),
            )
        player.acceptCard(Card(Suit.HEART, Rank.KING))
        assertThat(player.showCards()).isEqualTo(actual)
    }

    @Test
    fun `플레이어는 21 미만일 경우 히트 상태를 반환한다`() {
        val handState = player.getHandsState()
        val actual = HandState.HIT
        assertThat(handState).isEqualTo(actual)
    }

    @Test
    fun `플레이어는 21이고 두장일 경우에 블랙잭 상태를 반환한다`() {
        player = Player("동전", Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.ACE))
        val handState = player.getHandsState()
        val actual = HandState.BLACKJACK
        assertThat(handState).isEqualTo(actual)
    }

    @Test
    fun `플레이어는 21 초과일 경우 버스트를 반환한다`() {
        player = Player("동전", Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.SIX))
        val handState = player.getHandsState()
        val actual = HandState.BUST
        assertThat(handState).isEqualTo(actual)
    }
}
