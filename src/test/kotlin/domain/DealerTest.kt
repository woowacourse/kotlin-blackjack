package domain

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import state.RateOfProfit

class DealerTest {
    @Test
    fun `카드의 총합이 16이하면 카드를 더 받아야 한다`() {
        val dealer = Dealer()
        dealer.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        dealer.addCard(Card.of(CardCategory.SPADE, CardNumber.SIX))
        val actual = dealer.isPossibleDrawCard()
        assertThat(actual).isTrue
    }

    @Test
    fun `딜러로부터 플레이어의 수익률이 계산된다`() {
        val dealer = Dealer()
        dealer.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        dealer.addCard(Card.of(CardCategory.SPADE, CardNumber.SIX))
        dealer.addCard(Card.of(CardCategory.CLOVER, CardNumber.SIX))
        dealer.stay()

        val player = Player("mendel", 10000)
        player.addCard(Card.of(CardCategory.CLOVER, CardNumber.SIX))
        player.addCard(Card.of(CardCategory.SPADE, CardNumber.EIGHT))
        player.addCard(Card.of(CardCategory.SPADE, CardNumber.FIVE))
        player.stay()

        val actual = dealer.getProfit(player)
        val expected = RateOfProfit.LOSE_PROFIT
        assertThat(actual).isEqualTo(expected)
    }
}
