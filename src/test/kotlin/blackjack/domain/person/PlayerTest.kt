package blackjack.domain.person

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Deck
import blackjack.domain.generateCustomDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var deck: Deck
    private lateinit var player: Player

    @BeforeEach
    fun setup() {
        player = Player("pobi")
    }

    @Test
    fun `플레이어는 이름을 가지고 있어야 한다`() {
        player.name shouldBe "pobi"
    }

    @Test
    fun `카드를 draw하면 Player 보유한 카드 수는 1장이다`() {
        deck = generateCustomDeck()

        player.draw(deck)

        player.cards.size shouldBe 1
    }

    @Test
    fun `Player의 상태가 STAY라면 카드를 뽑을 수 없다`() {
        player.changeToStay()

        player.canDraw shouldBe false
    }

    @Test
    fun `버스트가 된 경우 카드를 뽑을 수 없다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.JACK)
        deck = generateCustomDeck(customCards)

        repeat(customCards.size) { player.draw(deck) }

        player.canDraw shouldBe false
    }
}
