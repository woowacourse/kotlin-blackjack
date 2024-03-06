package blackjack

import blackjack.model.CardMachineManager
import blackjack.model.Deck
import blackjack.model.PlayerCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerCardsTest {
    @Test
    fun `Cards는 새로운 카드를 가질 수 있다`(){
        val playerCards = PlayerCards(Deck())
        playerCards.add()
        assertThat(playerCards.cards.size).isEqualTo(3)
    }

    @Test
    fun `Cards 점수를 계산할 수 있다`(){
        CardMachineManager.machine = TestCardMachine()
        val cards = PlayerCards(Deck())
        assertThat(cards.calculateCardScore()).isEqualTo(TEST_INIT_CARD_SCORE)
    }

    companion object {
        private const val TEST_INIT_CARD_SCORE = 5
    }
}
