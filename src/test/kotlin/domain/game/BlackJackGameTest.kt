package domain.game

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import domain.card.CardPicker
import domain.participant.BettingMoney
import domain.participant.Name
import domain.participant.Names
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    fun Names(vararg names: String): Names {
        return Names(names.map(::Name))
    }

    class TestCardPicker() : CardPicker {
        override fun draw(): Card {
            return Card.of(cardCategory = CardCategory.CLOVER, cardNumber = CardNumber.FIVE)
        }
    }

    @Test
    fun `run 을 실행시켰을 때 result값이 제대로 나오는지 확인`() {
        // given
        val names = Names("pobi", "Scott")
        val bettingMoney = BettingMoney(1000)
        val blackJackGame = BlackJackGame({ names }, { bettingMoney }, TestCardPicker())
        // when
        val gameResult = blackJackGame.runGame({ false }, {}, {})
        val actual = gameResult.getDealerProfit().second
        // then
        assertThat(actual).isEqualTo(2000.0)
    }
}
