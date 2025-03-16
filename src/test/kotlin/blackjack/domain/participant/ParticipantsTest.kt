package blackjack.domain.participant

import blackjack.domain.ACE_HEART
import blackjack.domain.SEVEN_HEART
import blackjack.domain.SIX_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.card.CardCount
import blackjack.model.participant.Dealer
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.model.winning.WinningState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    private lateinit var participants: Participants

    @BeforeEach
    fun setup() {
        participants =
            Participants.create(
                dealerName = Name("딜러"),
                distributeCards = { listOf() },
                getPlayerNames = { listOf(Name("공백")) },
            )
    }

    @Test
    fun `플레이어 수가 0명 이하이면 예외가 발생한다`() {
        // given
        val dealer = Dealer.create(Name("딜러"))

        // when & then
        assertThrows<IllegalArgumentException> {
            Participants(dealer, Players.from(emptyList()))
        }
    }

    @Test
    fun `딜러 1명과 참가자 2명을 포함한 참가자 객체를 정상적으로 생성한다`() {
        // given
        val distributeCards = { _: CardCount -> listOf(TEN_HEART, SIX_HEART) }
        val getPlayerNames = { listOf(Name("공백"), Name("오이")) }

        // when
        participants = Participants.create(Name("딜러"), distributeCards, getPlayerNames)

        // then
        assertEquals("딜러", participants.dealer.name.value)
        assertEquals(2, participants.players.value.size)
        assertTrue(participants.players.value.any { it.name.value == "공백" })
        assertTrue(participants.players.value.any { it.name.value == "오이" })
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 같으면 무승부를 반환한다`() {
        // given
        participants.dealer.receiveCards { listOf(ACE_HEART, SEVEN_HEART) }
        participants.players.value
            .first()
            .receiveCards { listOf(ACE_HEART, SEVEN_HEART) }

        // when
        val playerResult = participants.winningResult().playersResult

        // then
        assertThat(playerResult.value.values.first()).isEqualTo(WinningState.PUSH)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 높으면 우승을 반환한다`() {
        // given
        participants.dealer.receiveCards { listOf(ACE_HEART, SIX_HEART) }
        participants.players.value
            .first()
            .receiveCards { listOf(ACE_HEART, SEVEN_HEART) }

        // when
        val playerResult = participants.winningResult().playersResult

        // then
        assertThat(playerResult.value.values.first()).isEqualTo(WinningState.WIN_DEFAULT)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 낮으면 패배를 반환한다`() {
        // given
        participants.dealer.receiveCards { listOf(ACE_HEART, SEVEN_HEART) }
        participants.players.value
            .first()
            .receiveCards { listOf(ACE_HEART, SIX_HEART) }

        // when
        val playerResult = participants.winningResult().playersResult

        // then
        assertThat(playerResult.value.values.first()).isEqualTo(WinningState.LOSE)
    }

    @Test
    fun `딜러와 플레이어가 모두 버스트된 경우 플레이어는 패배한다`() {
        // given
        participants.dealer.receiveCards { listOf(TEN_HEART, TEN_HEART, TEN_HEART) }
        participants.players.value
            .first()
            .receiveCards { listOf(TEN_HEART, TEN_HEART, TEN_HEART) }

        // when
        val playerResult = participants.winningResult().playersResult

        // then
        assertThat(playerResult.value.values.first()).isEqualTo(WinningState.LOSE)
    }
}
