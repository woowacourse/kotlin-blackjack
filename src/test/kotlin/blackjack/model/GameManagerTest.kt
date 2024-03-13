package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `블랙잭 게임 초기상태 설정 확인 테스트, (2장씩 갖고 있어야 함)`() {
        val users =
            listOf(
                Dealer(),
                Player("누누"),
                Player("채드"),
                Player("꼬상"),
            )
        val participants =
            Participants(
                participants =
                users,
            )
        val gameManager = GameManager(participants)
        gameManager.setGame()
        assertThat(participants.getDealer().getCards().size).isEqualTo(GameManager.INIT_HAND_CARD_COUNT)
        participants.getPlayers().forEach { participant ->
            assertThat(participant.getCards().size).isEqualTo(GameManager.INIT_HAND_CARD_COUNT)
        }
    }
}