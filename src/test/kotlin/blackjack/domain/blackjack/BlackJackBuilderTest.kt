package blackjack.domain.blackjack

import blackjack.domain.participants.user.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackBuilderTest {
    @Test
    fun `BlackJack을 초기화 할 수 있다`() {
        val data = blackJackData {
            participants {
                dealer()
                guest(Name("아크"))
                guest(Name("로피"))
            }
            initDrawAll()
        }
        assertAll(
            { assertThat(data.guests[0].name.toString()).isEqualTo("아크") },
            { assertThat(data.guests[0].state.size).isEqualTo(2) },
            { assertThat(data.guests[1].name.toString()).isEqualTo("로피") },
            { assertThat(data.guests[1].state.size).isEqualTo(2) },
            { assertThat(data.dealer.name.toString()).isEqualTo("딜러") },
            { assertThat(data.dealer.state.size).isEqualTo(2) },
            { assertThat(data.cardDeck.size).isEqualTo(46) },
        )
    }
}
