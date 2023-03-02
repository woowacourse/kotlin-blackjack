package blackjack.domain

import domain.CardGame
import domain.CardPackGenerator
import domain.CardPicker
import model.Name
import model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardGameTest {
    @Test
    fun `카드를 플레이어별로 2장씩 랜덤으로 지급한다`() {
        val game = CardGame(CardPicker(CardPackGenerator().createCards()))
        val player1 = Player(Name("jason"), game.pickTwice())
        val player2 = Player(Name("pobi"), game.pickTwice())
        assertThat(player1.cards.cards.size).isEqualTo(2)
        assertThat(player2.cards.cards.size).isEqualTo(2)
    }
}
