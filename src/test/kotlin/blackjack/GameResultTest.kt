package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.GameResultStatus
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `딜러와 플레이어 중 카드의 총합이 큰 사람이 이긴다`() {
        val dealer = Dealer()
        dealer.addCard(Card.of(Rank.NINE, Suit.CLUB))
        dealer.addCard(Card.of(Rank.TEN, Suit.SPADE))

        val player = Player("a")
        player.addCard(Card.of(Rank.TEN, Suit.CLUB))
        player.addCard(Card.of(Rank.TEN, Suit.SPADE))
        val gameResult = GameResult(dealer, listOf(player))
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(GameResultStatus.PLAYER_WIN)
    }
}
