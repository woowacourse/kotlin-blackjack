package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackBuilderTest {
    @Test
    fun `세팅이 된다`() {
        val blackJack = BlackJackBuilder.init {
            cardDeck(Card.all().shuffled())
            participants {
                dealer()
                guests(listOf("아크", "로피"))
            }
            draw()
        }
        assertAll(
            { assertThat(blackJack.guests[0].name.toString()).isEqualTo("아크") },
            { assertThat(blackJack.guests[0].cards.size).isEqualTo(2) },
            { assertThat(blackJack.guests[1].name.toString()).isEqualTo("로피") },
            { assertThat(blackJack.guests[1].cards.size).isEqualTo(2) },
            { assertThat(blackJack.dealer.name.toString()).isEqualTo("딜러") },
            { assertThat(blackJack.dealer.cards.size).isEqualTo(2) },
            { assertThat(blackJack.cardDeck.size).isEqualTo(46) },
        )
    }
}
