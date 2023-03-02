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
                dealer("딜러")
                users(listOf("아크", "로피"))
            }
            draw()
        }
        assertAll(
            { assertThat(blackJack.users[0].name.toString()).isEqualTo("아크") },
            { assertThat(blackJack.users[0].cards.size).isEqualTo(2) },
            { assertThat(blackJack.users[1].name.toString()).isEqualTo("로피") },
            { assertThat(blackJack.users[1].cards.size).isEqualTo(2) },
            { assertThat(blackJack.dealer.name.toString()).isEqualTo("딜러") },
            { assertThat(blackJack.dealer.cards.size).isEqualTo(2) },
            { assertThat(blackJack.cardDeck.size).isEqualTo(46) },
        )
    }
}
