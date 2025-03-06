package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Blackjack(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun start() {
        dealer.getCard()
        dealer.giveCard(players)
        dealer.giveCard(players)
    }

    fun waitForPlayers() {
        players.forEach { player ->
            player.hitOrStay {
                dealer.giveCard(listOf(player))
            }
        }

        dealer.hitOrStay()
    }
}

class BlackjackTest {
    @Test
    fun `게임을 시작하면 딜러는 한 장의 카드를 지급받는다`() {
        val dealer = Dealer()
        val game = Blackjack(dealer, emptyList())
        game.start()
        assertThat(dealer.getCountOfCards()).isEqualTo(1)
    }

    @Test
    fun `게임을 시작하면 플레이어는 두 장의 카드를 지급받는다`() {
        val dealer = Dealer()
        val gio = Player("Gio")
        val eden = Player("Eden")
        val players = listOf(gio, eden)
        val game = Blackjack(dealer, players)
        game.start()
        assertThat(gio.getCountOfCards()).isEqualTo(2)
        assertThat(eden.getCountOfCards()).isEqualTo(2)
    }

    @Test
    fun `모든 플레이어들의 결정이 끝나면 딜러는 숫자 합이 17 이상이 될 수 있을 때까지 카드를 받는다`() {
        val dealer = Dealer()
        val gio = Player("Gio")
        val eden = Player("Eden")
        val players = listOf(gio, eden)
        val game = Blackjack(dealer, players)
        game.start()
        game.waitForPlayers()
        assertThat(dealer.getScore()).isGreaterThanOrEqualTo(17)
    }
}
