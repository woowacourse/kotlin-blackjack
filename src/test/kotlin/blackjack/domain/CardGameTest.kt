package blackjack.domain

import domain.CardGame
import domain.CardPackGenerator
import domain.CardPicker
import model.Name
import model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class CardGameTest {

    @Test
    fun `카드를 플레이어별로 2장씩 랜덤으로 지급한다`() {
        val player1 = Player(game.pickTwice(), Name("jason"))
        val player2 = Player(game.pickTwice(), Name("pobi"))
        assertThat(player1.cards.size).isEqualTo(2)
        assertThat(player2.cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어 두 명의 정보를 생성한다`() {
        val players = game.initPlayers(listOf(Name("jason"), Name("pobi")))
        assertThat(players.size).isEqualTo(2)
        assertThat(players[0].name.value).isEqualTo("jason")
        assertThat(players[0].cards.size).isEqualTo(2)
        assertThat(players[1].name.value).isEqualTo("pobi")
        assertThat(players[1].cards.size).isEqualTo(2)
    }

    @Test
    fun `딜러의 정보를 생성한다`() {
        val dealer = game.initDealer()
        assertThat(dealer.cards.size).isEqualTo(2)
    }

    companion object {
        private lateinit var game: CardGame

        @BeforeAll
        @JvmStatic
        internal fun setUp() {
            game = CardGame(CardPicker(CardPackGenerator().createCardDeck()))
        }
    }
}
