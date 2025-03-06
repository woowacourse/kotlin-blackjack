package blackjack.domain

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.person.Hand
import blackjack.domain.person.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var deck: Deck
    private lateinit var player: Player

    @BeforeEach
    fun setup() {
        deck = Deck()
        player = Player("pobi")
    }

    @Test
    fun `플레이어는 이름을 가지고 있어야 한다`() {
        player.name shouldBe "pobi"
    }

    @Test
    fun `처음 턴에서 카드를 2장 뽑는다`() {
        player.draw(deck)
        player.cards().size shouldBe GameRule.FIRST_TURN_DRAW_AMOUNT
    }

    @Test
    fun `추가로 카드를 뽑을 때 1장 뽑는다`() {
        player.draw(deck)
        player.draw(deck)
        player.cards().size shouldBe 3
    }

    @Test
    fun `카드를 뽑지 않을 경우 상태가 변한다`() {
        player.draw(deck, false)
        player.canDraw() shouldBe false
    }

    @Test
    fun `버스트가 된 경우 카드를 뽑을 수 없다`() {
        val hand = Hand()
        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        player = Player("test", hand)
        player.draw(deck)

        player.canDraw() shouldBe false
    }
}
