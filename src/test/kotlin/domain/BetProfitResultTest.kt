package domain

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import domain.result.BetProfitResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BetProfitResultTest {
    @Test
    fun `베팅에 대한 플레이어와 딜러의 수익 결과를 올바르게 반환한다`() {
        val pobi = Player(NameAndBet(Name("pobi"), 10000))
        pobi.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        pobi.addCard(Card.of(CardCategory.SPADE, CardNumber.NINE))
        pobi.stay()

        val jason = Player(NameAndBet(Name("jason"), 20000))
        jason.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        jason.addCard(Card.of(CardCategory.SPADE, CardNumber.TWO))
        jason.stay()

        val dealer = Dealer()
        dealer.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        dealer.addCard(Card.of(CardCategory.SPADE, CardNumber.THREE))
        dealer.stay()

        val participants = Participants(
            Players(pobi, jason),
            dealer
        )
        val betProfitResult = BetProfitResult(participants.players, participants.dealer)
        assertAll(
            "딜러와 플레이어의 베팅결과가 올바르게 나온다",
            {
                val result: Int = betProfitResult.playersResult.list[0].profitMoney
                assertThat(result).isEqualTo(10000)
            },
            {
                val result: Int = betProfitResult.playersResult.list[1].profitMoney
                assertThat(result).isEqualTo(-20000)
            },
            {
                val result: Int = betProfitResult.dealerResult
                assertThat(result).isEqualTo(10000)
            },
        )
    }
}
