package blackjack.domain.game

import blackjack.domain.ACE_HEART
import blackjack.domain.SEVEN_HEART
import blackjack.domain.SIX_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.model.winning.WinningState.LOSE
import blackjack.model.winning.WinningState.PUSH
import blackjack.model.winning.WinningState.WIN_DEFAULT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningManagerTest {
    private lateinit var participants: Participants
    private lateinit var winningManager: WinningManager

    @BeforeEach
    fun setup() {
        winningManager = WinningManager()

        participants = Participants(Dealer.create(), Players.from("공백"))
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 같으면 무승부를 반환한다`() {
        // given
        participants.dealer.addAll(
            listOf(ACE_HEART, SEVEN_HEART),
        )

        participants.players.value.first().addAll(
            listOf(ACE_HEART, SEVEN_HEART),
        )

        // when
        val playerResult = winningManager.result(participants).playersResults

        // then
        assertThat(playerResult.values.first()).isEqualTo(PUSH)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 높으면 우승을 반환한다`() {
        // given
        participants.dealer.addAll(
            listOf(ACE_HEART, SIX_HEART),
        )

        participants.players.value.first().addAll(
            listOf(ACE_HEART, SEVEN_HEART),
        )

        // when
        val playerResult = winningManager.result(participants).playersResults

        // then
        assertThat(playerResult.values.first()).isEqualTo(WIN_DEFAULT)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 낮으면 패배를 반환한다`() {
        // given
        participants.dealer.addAll(
            listOf(ACE_HEART, SEVEN_HEART),
        )

        participants.players.value.first().addAll(
            listOf(ACE_HEART, SIX_HEART),
        )

        // when
        val playerResult = winningManager.result(participants).playersResults

        // then
        assertThat(playerResult.values.first()).isEqualTo(LOSE)
    }

    @Test
    fun `딜러와 플레이어가 모두 버스트된 경우 플레이어는 패배한다`() {
        // given
        participants.dealer.addAll(
            listOf(TEN_HEART, TEN_HEART, TEN_HEART),
        )

        participants.players.value.first().addAll(
            listOf(TEN_HEART, TEN_HEART, TEN_HEART),
        )

        // when
        val playerResult = winningManager.result(participants).playersResults

        // then
        assertThat(playerResult.values.first()).isEqualTo(LOSE)
    }
}
