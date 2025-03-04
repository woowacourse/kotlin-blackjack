package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `이름과 패를 가지고 있다`() {
        val player = Player("pobi")
        player.name shouldBe "pobi"
    }

    @Test
    fun `덱에서 카드를 뽑아서 패에 가져온다`() {
        val player = Player("pobi")
        val deck = Deck()

        player.draw(deck, 1)

        player.hand.cards.size shouldBe 1
    }
}
