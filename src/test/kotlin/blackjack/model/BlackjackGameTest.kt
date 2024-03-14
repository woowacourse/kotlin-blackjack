package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suite
import blackjack.model.card.TestCardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `게임 시작 시 딜러와 플레이어가 2장의 카드를 받는다`() {
        // given
        val dealer = Dealer()
        val players = Players.of(listOf("olive", "seogi"), listOf(1000, 1000))

        // when
        BlackjackGame(dealer, players, TestCardProvider)

        // then
        val expected = listOf(Card.of(Denomination.TWO, Suite.HEART), Card.of(Denomination.TWO, Suite.HEART))
        assertThat(dealer.getCards()).isEqualTo(expected)
        players.playerGroup.forEach {
            assertThat(it.getCards()).isEqualTo(expected)
        }
    }
}
