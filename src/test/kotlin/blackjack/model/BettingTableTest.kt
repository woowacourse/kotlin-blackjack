package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.participant.Player
import blackjack.model.state.Blackjack
import blackjack.model.state.Hit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BettingTableTest {
    @Test
    fun `게임이 끝난 상태이면 수익률을 계산할 수 있다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE), Card(CardShape.SPADE, Denomination.TEN))
        val bettingTable = BettingTable(Player("모찌", Blackjack(hand)), Money(1000))
        val actaul = bettingTable.getProfit()

        val expected = Money(1500)

        Assertions.assertThat(actaul).isEqualTo(expected)
    }

    @Test
    fun `게임이 진행되는 상태이면 에러를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.KING), Card(CardShape.SPADE, Denomination.TEN))
        val bettingTable = BettingTable(Player("모찌", Hit(hand)), Money(1000))
        org.junit.jupiter.api.assertThrows<IllegalStateException> {
            bettingTable.getProfit()
        }
    }
}
