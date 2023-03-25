package domain.result

import model.domain.card.Card
import model.domain.card.Deck
import model.domain.result.Profit
import model.tools.CardNumber
import model.tools.Money
import model.tools.Participant
import model.tools.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitTest {

    @Test
    fun `유저1-13점(패, -5000), 유저2-블랙잭(승, 25000), 유저3-버스트(패, -1000), 딜러19점(-19000)`() {
        // given
        val participant = Participant.from { listOf("산군", "말리빈", "레아") }
        val dealer = participant.dealer.apply {
            draw(Deck.create(listOf(Card.of(Shape.DIAMONDS, CardNumber.NINE))))
            draw(Deck.create(listOf(Card.of(Shape.DIAMONDS, CardNumber.TEN))))
        }
        val firstUser = participant.user[0].apply {
            betMoney(Money(5000))
            draw(Deck.create(listOf(Card.of(Shape.DIAMONDS, CardNumber.EIGHT))))
            draw(Deck.create(listOf(Card.of(Shape.DIAMONDS, CardNumber.THREE))))
            draw(Deck.create(listOf(Card.of(Shape.DIAMONDS, CardNumber.TWO))))
        }
        val secondUser = participant.user[1].apply {
            betMoney(Money(50000))
            draw(Deck.create(listOf(Card.of(Shape.DIAMONDS, CardNumber.ACE))))
            draw(Deck.create(listOf(Card.of(Shape.CLUBS, CardNumber.KING))))
        }
        val thirdUser = participant.user[2].apply {
            betMoney(Money(1000))
            draw(Deck.create(listOf(Card.of(Shape.DIAMONDS, CardNumber.EIGHT))))
            draw(Deck.create(listOf(Card.of(Shape.CLUBS, CardNumber.KING))))
            draw(Deck.create(listOf(Card.of(Shape.CLUBS, CardNumber.NINE))))
        }

        // when
        Profit(participant).calculate()
        val actual = listOf(
            dealer.money.amount,
            firstUser.money.amount,
            secondUser.money.amount,
            thirdUser.money.amount,
        )

        // then
        assertThat(actual).isEqualTo(listOf(-19000, -5000, 25000, -1000))
    }
}
