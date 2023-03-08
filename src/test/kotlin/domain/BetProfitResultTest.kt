package domain

import domain.result.BetProfitResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BetProfitResultTest {
    @Test
    fun `베팅에 대한 플레이어와 딜러의 수익 결과를 올바르게 반환한다`() {
        val pobi = Player(
            NameAndBet(Name("pobi"), 10000),
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.SPADE, CardNumber.NINE)
            )
        )

        val jason = Player(
            NameAndBet(Name("jason"), 20000),
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.SPADE, CardNumber.TWO)
            )
        )

        val participants = Participants(
            Players(
                pobi,
                jason
            ),
            Dealer(
                Cards(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.THREE)
                )
            )
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
