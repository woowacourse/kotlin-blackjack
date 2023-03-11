package blackjack.domain.blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.card.Cards
import blackjack.domain.participants.*
import blackjack.domain.result.Outcome
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `게임 결과를 반환한다`() {
        val blackJack = BlackJack(
            participants = Participants(Dealer(), listOf(Guest(Name("아크")), Guest(Name("로피")))),
            cardDeck = CardDeck(Cards.all()),
        ).apply {
            dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
            dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
            guests[0].draw(Card(CardMark.CLOVER, CardValue.NINE))
            guests[0].draw(Card(CardMark.CLOVER, CardValue.NINE))
            guests[1].draw(Card(CardMark.CLOVER, CardValue.ACE))
            guests[1].draw(Card(CardMark.CLOVER, CardValue.QUEEN))
        }
        assertThat(blackJack.getResult()).isEqualTo(listOf(Outcome.LOSE, Outcome.BLACKJACK))
    }

    @Test
    fun `유저들의 베팅한 금액을 반환한다`() {
        val guest = Guest(Name("아크"))
        val blackJack = BlackJack(
            participants = Participants(Dealer(), listOf(guest)),
            cardDeck = CardDeck(Cards.all()),
        )
        val usersBettingMoney = blackJack.betMoney(::inputBettingMoney)

        assertThat(usersBettingMoney.getUserBettingMoney(guest)).isEqualTo(BettingMoney(20000))
    }

    private fun inputBettingMoney(string: String): Int {
        return 20000
    }
}
