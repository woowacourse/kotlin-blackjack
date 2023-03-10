package blackjack.domain.blackjack

import blackjack.domain.participants.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackSettingBuilderTest {
    @Test
    fun `BlackJack을 초기화 할 수 있다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guest(Name("아크"))
                guest(Name("로피"))
            }
            initDrawAll()
        }
        assertAll(
            { assertThat(blackJack.guests[0].name.toString()).isEqualTo("아크") },
            { assertThat(blackJack.guests[0].state.size).isEqualTo(2) },
            { assertThat(blackJack.guests[1].name.toString()).isEqualTo("로피") },
            { assertThat(blackJack.guests[1].state.size).isEqualTo(2) },
            { assertThat(blackJack.dealer.name.toString()).isEqualTo("딜러") },
            { assertThat(blackJack.dealer.state.size).isEqualTo(2) },
            { assertThat(blackJack.cardDeck.size).isEqualTo(46) },
        )
    }
}
