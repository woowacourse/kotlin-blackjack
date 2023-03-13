package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FinalProfitTest {

    @Test
    fun `유저가 이겼을 경우 베팅금액의 1배의 최종 수익을 반환한다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.HEART, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.TWO))
        dealer.draw(Card(CardMark.DIA, CardValue.THREE))

        val guest = Guest(Name("로피"))
        guest.draw(Card(CardMark.HEART, CardValue.KING))
        guest.draw(Card(CardMark.CLOVER, CardValue.FIVE))
        guest.draw(Card(CardMark.DIA, CardValue.SIX))

        val usersBettingMoney = UsersBettingMoney(
            mapOf(
                guest to BettingMoney(10000),
            ),
        )
        val finalProfit = FinalProfit.playersFinalProfits(dealer, usersBettingMoney)
        assertThat(finalProfit.getUserProfit(guest)).isEqualTo(10000)
    }

    @Test
    fun `유저가 졌을 경우 베팅금액의 -1배의 최종 수익을 반환한다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.HEART, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.FIVE))
        dealer.draw(Card(CardMark.DIA, CardValue.FOUR))

        val guest = Guest(Name("로피"))
        guest.draw(Card(CardMark.HEART, CardValue.KING))
        guest.draw(Card(CardMark.CLOVER, CardValue.TWO))
        guest.draw(Card(CardMark.DIA, CardValue.THREE))

        val usersBettingMoney = UsersBettingMoney(
            mapOf(
                guest to BettingMoney(10000),
            ),
        )
        val finalProfit = FinalProfit.playersFinalProfits(dealer, usersBettingMoney)
        assertThat(finalProfit.getUserProfit(guest)).isEqualTo(-10000)
    }

    @Test
    fun `유저가 비겼을 경우 베팅금액의 0배의 최종 수익을 반환한다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.HEART, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.FIVE))
        dealer.draw(Card(CardMark.DIA, CardValue.FOUR))

        val guest = Guest(Name("로피"))
        guest.draw(Card(CardMark.HEART, CardValue.FIVE))
        guest.draw(Card(CardMark.CLOVER, CardValue.FOUR))
        guest.draw(Card(CardMark.DIA, CardValue.KING))

        val usersBettingMoney = UsersBettingMoney(
            mapOf(
                guest to BettingMoney(10000),
            ),
        )
        val finalProfit = FinalProfit.playersFinalProfits(dealer, usersBettingMoney)
        assertThat(finalProfit.getUserProfit(guest)).isEqualTo(0)
    }

    @Test
    fun `유저가 블랙잭일 경우 10000원 베팅할 경우 15000원의 최종 수익을 반환한다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.HEART, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.FIVE))

        val guest = Guest(Name("로피"))
        guest.draw(Card(CardMark.HEART, CardValue.ACE))
        guest.draw(Card(CardMark.CLOVER, CardValue.TEN))

        val usersBettingMoney = UsersBettingMoney(
            mapOf(
                guest to BettingMoney(10000),
            ),
        )
        val finalProfit = FinalProfit.playersFinalProfits(dealer, usersBettingMoney)
        assertThat(finalProfit.getUserProfit(guest)).isEqualTo(15000)
    }

    @Test
    fun `딜러가 패배하면 딜러는 모든 유저의 베팅금액만큼 잃는다`() {
        val finalProfit = FinalProfit(
            mapOf(
                Guest(Name("로피")) to 10000,
                Guest(Name("아크")) to 20000,
            ),
        )
        val dealerProfit = finalProfit.getDealerProfit()

        assertThat(dealerProfit).isEqualTo(-30000)
    }

    @Test
    fun `딜러가 승리하면 딜러는 모든 유저의 베팅금액만큼 얻는다`() {
        val finalProfit = FinalProfit(
            mapOf(
                Guest(Name("로피")) to -10000,
                Guest(Name("아크")) to -20000,
            ),
        )
        val dealerProfit = finalProfit.getDealerProfit()

        assertThat(dealerProfit).isEqualTo(30000)
    }
}
