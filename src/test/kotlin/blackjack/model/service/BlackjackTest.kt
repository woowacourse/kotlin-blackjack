package blackjack.model.service

import blackjack.model.domain.Dealer
import blackjack.model.domain.Deck
import blackjack.model.domain.Player
import blackjack.model.strategy.FalseShuffle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    private val player1 = Player("제리")
    private val player2 = Player("환노")
    private val dealer = Dealer()
    private val game = Blackjack()
    private val deck = Deck(FalseShuffle())

    @Test
    fun `게임 시작시 카드를 2장을 나눈다`() {
        game.initGame(listOf(player1, player2, dealer), deck)

        assertThat(dealer.cardDeck.size).isEqualTo(2)
        assertThat(player1.cardDeck.size).isEqualTo(2)
        assertThat(player2.cardDeck.size).isEqualTo(2)
    }
}
