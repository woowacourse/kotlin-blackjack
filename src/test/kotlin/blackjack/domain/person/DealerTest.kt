package blackjack.domain.person

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Deck
import blackjack.domain.generateCustomDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var deck: Deck

    @BeforeEach
    fun setup() {
        dealer = Dealer()
    }

    @Test
    fun `카드를 draw하면 dealer가 보유한 카드 수는 1이다`() {
        deck = generateCustomDeck()

        dealer.draw(deck)

        dealer.cards().size shouldBe 1
    }

    @Test
    fun `카드 숫자의 총 합이 16이하인 경우 카드를 뽑을 수 있다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.SIX)
        deck = generateCustomDeck(customCards)

        repeat(customCards.size) { dealer.draw(deck) }

        dealer.canDraw() shouldBe true
    }

    @Test
    fun `카드 숫자의 총 합이 17이상인 경우 카드를 뽑을 수 없다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.SEVEN)
        deck = generateCustomDeck(customCards)

        repeat(customCards.size) { dealer.draw(deck) }

        dealer.canDraw() shouldBe false
    }

    @Test
    fun `버스트가 된 경우 카드를 뽑을 수 없다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.JACK)
        deck = generateCustomDeck(customCards)

        repeat(customCards.size) { dealer.draw(deck) }

        dealer.canDraw() shouldBe false
    }
}
