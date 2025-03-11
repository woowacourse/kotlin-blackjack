package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.gameResult.GameResultStatus
import blackjack.domain.gameResult.PlayerResults
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultsTest {
    @Test
    fun `딜러와 플레이어 중 카드의 총합이 큰 사람이 이긴다`() {
        val dealer = Dealer()
        dealer.addCard(Card.of(Rank.NINE, Suit.CLUB))
        dealer.addCard(Card.of(Rank.TEN, Suit.SPADE))

        val player = Player("a")
        player.addCard(Card.of(Rank.TEN, Suit.CLUB))
        player.addCard(Card.of(Rank.TEN, Suit.SPADE))
        val playerResults = PlayerResults(dealer, listOf(player))
        assertThat(playerResults.judgePlayerResult(player)).isEqualTo(GameResultStatus.PLAYER_WIN)
    }
}
