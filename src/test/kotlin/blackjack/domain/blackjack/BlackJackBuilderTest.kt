package blackjack.domain.blackjack

import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackBuilderTest {
    @Test
    fun `블랙잭 게임 초기 시작단계가 실행된다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guests(listOf("아크", "로피"))
            }
            initDraw()
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
