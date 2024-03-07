package blackjack.model

import blackjack.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    class Participant(
        private val name: String,
        private val blackJack: BlackJack = BlackJack()
    ) {

        fun draw(card: Card) {
            blackJack.addCard(card)
        }

        fun checkHitState(): Boolean {
            return blackJack.state == State.Action.Hit
        }

        fun getName(): String {
            return name
        }

        fun getCards(): Set<Card> {
            return blackJack.getCards()
        }
    }

    @Test
    fun `정상적인 Hit 상태 체크 테스트 `(){
        val name = "딜러"
        val participant = Participant(name = name)
        assertThat(participant.checkHitState()).isTrue()
    }
}
