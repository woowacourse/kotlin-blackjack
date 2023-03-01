package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `게임 결과를 반환한다`() {
        val blackJack = BlackJack(
            participants = Participants(User("딜러"), listOf(User("아크"), User("로피"))),
            cardDeck = CardDeck(Card.all()),
        ).apply {
            dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
            dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
            users[0].draw(Card(CardMark.CLOVER, CardValue.NINE))
            users[0].draw(Card(CardMark.CLOVER, CardValue.NINE))
            users[1].draw(Card(CardMark.CLOVER, CardValue.ACE))
            users[1].draw(Card(CardMark.CLOVER, CardValue.QUEEN))
        }
        assertThat(blackJack.result).isEqualTo(listOf(Outcome.LOSE, Outcome.WIN))
    }
}
