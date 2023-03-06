package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드를 더 받아야 하는 상태인지 확인한다`() {
        val dealer = Dealer("aaa")
        dealer.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = dealer.checkProvideCardPossible()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `다른 플레이어들의 카드 숫자 합을 받아 자신의 승패 결과들을 업데이트한다`() {
        // given
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.EIGHT, CardShape.DIAMOND))
        dealer.addCard(Card(CardNumber.SEVEN, CardShape.HEART))
        val othersSum = listOf(15, 21, 8)

        // when
        dealer.updateResults(othersSum)

        // then
        assertThat(dealer.results).isEqualTo(mapOf(Result.WIN to 1, Result.LOSE to 1, Result.DRAW to 1))
    }
}
