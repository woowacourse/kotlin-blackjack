package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드를 8클로버만 가지고 있을 때, 카드를 더 받아야 하는 상태인지 확인하면, true이다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = dealer.checkProvideCardPossible()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `플레이어1은 Burst 플레이어2는 20 딜러는 17 일때, 딜러의 승패를 계산하면, 1승 1패이다`() {
        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )
        val player1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.SEVEN, CardShape.HEART),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )

        val player2 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.KING, CardShape.DIAMOND),
                    Card(CardNumber.QUEEN, CardShape.HEART)
                )
            )
        )

        // when
        val actual = dealer.calculateResults(Participants(listOf(player1, player2)))

        // then
        assertThat(actual.results).isEqualTo(listOf(Result.WIN, Result.LOSE))
    }

    @Test
    fun `플레이어1은 17 플레이어2는 20 딜러는 Burst 일때, 딜러의 승패를 계산하면, 2패이다`() {
        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.QUEEN, CardShape.DIAMOND),
                    Card(CardNumber.KING, CardShape.DIAMOND),
                    Card(CardNumber.THREE, CardShape.SPADE)
                )
            )
        )
        val player1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )

        val player2 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.HEART),
                    Card(CardNumber.QUEEN, CardShape.SPADE),
                    Card(CardNumber.TWO, CardShape.DIAMOND)
                )
            )
        )

        // when
        val actual = dealer.calculateResults(Participants(listOf(player1, player2)))

        // then
        assertThat(actual.results).isEqualTo(listOf(Result.LOSE, Result.LOSE))
    }
}
