package blackjack.model

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.model.participant.testState.Stay
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StayTest {
    @Test
    fun `게임에서 이겼을 경우, 배팅 금액의 1배를 반환한다`() {
        val deck = Deck(NormalCardMachine())
        val handCards = HandCards().also { it.add(deck.draw(2)) }
        val profit = Stay().getProfit(handCards, 1, BattingMoney.ofAmount(100.0))
        assertThat(profit.amount).isEqualTo(100.0 * 1)
    }

    @Test
    fun `게임에서 졌을 경우, 배팅 금액의 -1배를 반환한다`() {
        val deck = Deck(NormalCardMachine())
        val handCards = HandCards().also { it.add(deck.draw(2)) }
        val profit = Stay().getProfit(handCards, 12, BattingMoney.ofAmount(100.0))
        assertThat(profit.amount).isEqualTo(100.0 * -1)
    }
}
