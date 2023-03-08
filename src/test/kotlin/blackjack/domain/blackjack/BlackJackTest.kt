package blackjack.domain.blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.card.Cards
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.Participants
import blackjack.domain.result.Outcome
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `게임 결과를 반환한다`() {
        val blackJack = BlackJack(
            participants = Participants(Dealer(), listOf(Guest("아크"), Guest("로피"))),
            cardDeck = CardDeck(Cards.all()),
        ).apply {
            dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
            dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
            guests[0].draw(Card(CardMark.CLOVER, CardValue.NINE))
            guests[0].draw(Card(CardMark.CLOVER, CardValue.NINE))
            guests[1].draw(Card(CardMark.CLOVER, CardValue.ACE))
            guests[1].draw(Card(CardMark.CLOVER, CardValue.QUEEN))
        }
        assertThat(blackJack.getResult()).isEqualTo(listOf(Outcome.LOSE, Outcome.WIN))
    }
}
