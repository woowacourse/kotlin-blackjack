package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPackTest {

    @Test
    fun `카드팩에서 카드덱 56장을 모두 뽑으면 카드팩이 빈다`() {
        val cardPack = CardPack()

        repeat(56) { cardPack.draw() }
        assertThat(cardPack.isEmpty()).isTrue
    }

    @Test
    fun `카드팩이 비게되면 다음 카드 뽑기전 카드덱을 하나 추가하여 카드팩이 채워져있다`() {
        val cardPack = CardPack()
        val dealer = createDealer()

        repeat(56) { cardPack.draw() }
        dealer.drawCard(cardPack)
        assertThat(cardPack.isEmpty()).isFalse
    }

    private fun createDealer(): Dealer {
        return Dealer(CardHand(listOf(Card(CardNumber.TWO, Shape.SPADE), Card(CardNumber.FOUR, Shape.SPADE))))
    }
}
