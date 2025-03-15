package blackjack.domain.model

import blackjack.domain.model.card.Deck
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameTest {
    @Test
    fun `플레이어들의 이름 중 중복이 있을 시 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Game(Deck(), Dealer(), listOf(Player("A"), Player("A"))) }
    }

    @Test
    fun `딜러와 플레이어의 이름이 중복될 시 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Game(Deck(), Dealer("딜러"), listOf(Player("딜러"))) }
    }
}
