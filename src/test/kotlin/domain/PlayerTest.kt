package domain

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import state.RateOfProfit

class PlayerTest {
    @Test
    fun `21보다 작으면 더 받을 수 있도록 true를 반환한다`() {
        val player = Player(PlayerInfo(Name("scott"), 10000))
        player.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        player.addCard(Card.of(CardCategory.SPADE, CardNumber.NINE))
        val actual = player.isPossibleDrawCard()
        assertThat(actual).isTrue
    }

    @Test
    fun `게임 결과의 수익률을 알 수 있다`() {
        val player = Player(PlayerInfo(Name("scott"), 10000))
        player.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        player.addCard(Card.of(CardCategory.SPADE, CardNumber.NINE))
        player.stay()

        val dealer = Dealer()
        dealer.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        dealer.addCard(Card.of(CardCategory.SPADE, CardNumber.NINE))
        dealer.addCard(Card.of(CardCategory.CLOVER, CardNumber.FOUR))
        dealer.stay()

        val result = dealer.getProfit(player)
        val expected = RateOfProfit.LOSE
        assertThat(result).isEqualTo(expected)
    }
}
