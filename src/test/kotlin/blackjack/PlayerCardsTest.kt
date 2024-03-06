package blackjack

import blackjack.model.CardMachineManager
import blackjack.model.Deck
import blackjack.model.PlayerCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerCardsTest {
    @Test
    fun `Cards는 새로운 카드를 가질 수 있다`() {
        val playerCards = PlayerCards(Deck())
        playerCards.add()
        assertThat(playerCards.cards.size).isEqualTo(INIT_CARD_SIZE + 1)
    }

    @Test
    fun `Cards 점수를 계산할 수 있다`() {
        CardMachineManager.machine = TestCardMachine()
        val cards = PlayerCards(Deck())
        assertThat(cards.calculateCardScore()).isEqualTo(TEST_INIT_CARD_SCORE)
    }

    @Test
    fun `ACE가 있을 경우, 총합이 21보다 작으면 11로 계산해서 반환한다`() {
        CardMachineManager.machine = TestCardMachine()
        val cards = PlayerCards(Deck())
        cards.add() // Ace 추가
        assertThat(cards.calculateCardScore()).isEqualTo(TEST_INIT_CARD_SCORE + 11)
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
        private const val TEST_INIT_CARD_SCORE = 10
    }
}
