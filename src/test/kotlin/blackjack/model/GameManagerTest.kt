package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    class GameManger {
        private val cardDeck = CardDeck()

        fun setGame(participants: Participants) {
            repeat(INIT_HAND_CARD_COUNT) {
                participants.getParticipants().forEach { participant ->
                    participant.draw(cardDeck.draw())
                }
            }
        }

        fun applyUserDrawDecision(participant: Participant) {
            participant.draw(cardDeck.draw())
        }

        companion object {
            const val INIT_HAND_CARD_COUNT: Int = 2
        }
    }

    @Test
    fun `블랙잭 게임 초기상태 설정 확인 테스트, (2장씩 갖고 있어야 함)`() {
        val gameManager = GameManger()
        val participants = Participants(
            participants = listOf(Dealer(), Player("누누"), Player("채드"), Player("꼬상"))
        )
        gameManager.setGame(participants)
        participants.getParticipants().forEach { participant ->
            assertThat(participant.getCards().size).isEqualTo(GameManger.INIT_HAND_CARD_COUNT)
        }
    }
}
