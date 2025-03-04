package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `player는 이름, 상태를 가지고 있다`() {
        val player = Player("pobi")
        player.name shouldBe "pobi"
        player.gameState shouldBe GameState.HIT
    }

    @Test
    fun `요청한 수량에 맞게 카드를 덱에서 뽑아 패에 추가할수 있다`() {
        val deck = Deck()
        val player = Player("pobi")
        player.draw(deck, 2)
        player.hand.cards.size shouldBe 2
    }
}
