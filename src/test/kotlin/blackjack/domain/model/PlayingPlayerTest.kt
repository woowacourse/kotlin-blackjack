package blackjack.domain.model

import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_SEVEN
import blackjack.domain.HEART_ACE
import blackjack.domain.HEART_FOUR
import blackjack.domain.HEART_KING
import blackjack.domain.HEART_SIX
import blackjack.domain.model.hand.BlackJack
import blackjack.domain.model.hand.Bust
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.PlayerHit
import blackjack.domain.model.hand.State
import blackjack.domain.model.hand.Stay
import blackjack.domain.model.playing.PlayingPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayingPlayerTest {
    private lateinit var playingPlayer: PlayingPlayer
    private lateinit var state: State

    @BeforeEach
    fun setUp() {
        playingPlayer = PlayingPlayer("동전", HEART_ACE)
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(playingPlayer.name).isEqualTo("동전")
    }

    @Test
    fun `플레이어는 입력 받은 숫자 만큼 반환한다 `() {
        assertThat(playingPlayer.showCards()).isEqualTo(listOf(HEART_ACE))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val actual =
            listOf(
                HEART_ACE,
                HEART_KING,
            )
        playingPlayer.acceptCard(HEART_KING)
        assertThat(playingPlayer.showCards()).isEqualTo(actual)
    }

    @Test
    fun `플레이어는 20 이하일 경우 HandsState가 PlayerHit가 된다`() {
        val handState = playingPlayer.handsState
        playingPlayer.acceptCard(HEART_SIX) // score 17
        assertThat(handState).isInstanceOf(PlayerHit::class.java)
    }

    @Test
    fun `플레이어는 21이고 두장일 경우에 HandsState가 BlackJack이 된다`() {
        playingPlayer.acceptCard(HEART_KING) // score 21
        assertThat(playingPlayer.handsState).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `플레이어는 21 초과일 경우 HandsState가 Bust가 된다`() {
        playingPlayer.acceptCard(HEART_SIX)
        playingPlayer.acceptCard(CLUB_SEVEN)
        playingPlayer.acceptCard(CLUB_KING) // score 24
        assertThat(playingPlayer.handsState).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `플레이어는 21일 경우 HandsState가 Stay가 된다`() {
        playingPlayer.acceptCard(HEART_SIX)
        playingPlayer.acceptCard(Card(Suit.CLUB, Rank.FOUR)) // score 21
        assertThat(playingPlayer.handsState).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `다른 상태가 버스트여도 본인이 버스트 일 경우에 패배한다`() {
        playingPlayer.acceptCard(HEART_SIX)
        playingPlayer.acceptCard(CLUB_SEVEN)
        playingPlayer.acceptCard(CLUB_KING) // score 24
        state = Bust(Hands(HEART_KING, CLUB_KING, CLUB_SEVEN))
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `둘다 블랙잭 일 경우에 비긴다`() {
        playingPlayer.acceptCard(HEART_KING)
        state = BlackJack(Hands(HEART_ACE, CLUB_KING))
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.DRAW)
    }

    @Test
    fun `점수가 같아도 나만 블랙잭일 경우에 블랙잭이 된다`() {
        playingPlayer.acceptCard(HEART_KING)
        state = Stay(Hands(HEART_FOUR, CLUB_KING, CLUB_SEVEN))
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.BLACKJACK)
    }

    @Test
    fun `스테이 상태에서 점수가 같더라도 상대가 블랙잭일 경우에 패배한다`() {
        playingPlayer.acceptCard(HEART_FOUR)
        playingPlayer.acceptCard(HEART_SIX)
        state = BlackJack(Hands(HEART_ACE, CLUB_KING))
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `스테이 상태에서 상대가 버스트 일 경우 승리한다`() {
        playingPlayer.acceptCard(HEART_FOUR)
        playingPlayer.acceptCard(HEART_SIX)
        state = Bust(Hands(HEART_KING, CLUB_KING, CLUB_SEVEN))
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.WIN)
    }

    @Test
    fun `둘다 스테이 상태에서 점수가 높음면 승리한다`() {
        playingPlayer.acceptCard(CLUB_SEVEN) // 18
        state = Stay(Hands(HEART_KING, CLUB_SEVEN)) // 17
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.WIN)
    }

    @Test
    fun `둘다 스테이 상태에서 점수가 낮으면 패배한다`() {
        playingPlayer.acceptCard(HEART_FOUR) // 15
        state = Stay(Hands(HEART_KING, CLUB_SEVEN)) // 17
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `둘다 스테이 상태에서 점수가 같으면 비긴다`() {
        playingPlayer.acceptCard(HEART_SIX) // 17
        state = Stay(Hands(HEART_KING, CLUB_SEVEN)) // 17
        assertThat(playingPlayer.match(state)).isEqualTo(MatchResult.DRAW)
    }
}
