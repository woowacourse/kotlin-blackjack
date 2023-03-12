package entity

import entity.card.Card
import entity.card.CardNumber
import entity.card.CardType
import entity.card.Cards
import entity.result.BettingMoney
import entity.users.Dealer
import entity.users.UserInformation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 가진 카드의 숫자 합이 16 이하이면 한장의 카드를 더 받을 수 있다`() {
        val userInformation = UserInformation(Cards(listOf(Card(CardType.SPADE, CardNumber.TEN))), BettingMoney(0))
        val dealer = Dealer(userInformation)
        val isDistributable = dealer.isDistributable()

        assertThat(isDistributable).isTrue
    }

    @Test
    fun `딜러가 가진 카드의 숫자 합이 16 초과이면 한장의 카드를 더 받을 수 없다`() {
        val userInformation = UserInformation(Cards(listOf(Card(CardType.SPADE, CardNumber.TEN), Card(CardType.SPADE, CardNumber.SEVEN))), BettingMoney(0))
        val dealer = Dealer(userInformation)
        val isDistributable = dealer.isDistributable()

        assertThat(isDistributable).isFalse
    }
}
