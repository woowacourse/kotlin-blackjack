package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `이름과 패를 가지고 있다`() {
        val dealer = Dealer("pobi")
        dealer.name shouldBe "pobi"
    }

    @Test
    fun `요청한 수량에 맞게 카드를 덱에서 뽑아 패에 추가할수 있다`() {
        val deck = Deck()
        val dealer = Dealer("pobi")
        dealer.draw(deck, 2)
        dealer.hand.cards.size shouldBe 2
    }
}
