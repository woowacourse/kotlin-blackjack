package entity

import model.ManualCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 가진 카드의 숫자 합이 16 이하이면 한장의 카드를 더 받을 수 있다`() {
        val dealer = Dealer(Cards(listOf(Card(CardType.SPADE, CardNumber.TEN))))
        val isDistributable = dealer.isDistributable()

        assertThat(isDistributable).isTrue
    }

    @Test
    fun `딜러가 가진 카드의 숫자 합이 16 초과이면 한장의 카드를 더 받을 수 없다`() {
        val dealer = Dealer(Cards(listOf(Card(CardType.SPADE, CardNumber.TEN), Card(CardType.SPADE, CardNumber.SEVEN))))
        val isDistributable = dealer.isDistributable()

        assertThat(isDistributable).isFalse
    }

    @Test
    fun `딜러에게 카드 한장 분배한다`() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(
                CardType.CLUB to CardNumber.THREE,
                CardType.SPADE to CardNumber.QUEEN
            )
        )
        val dealer = Dealer()

        // when
        dealer.distribute(cardFactory) { }

        // then
        val except = listOf(
            Card(CardType.CLUB, CardNumber.THREE)
        )
        assertThat(dealer.cards.value).isEqualTo(except)
    }
}
