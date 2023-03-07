package domain

import blackjack.domain.CardPack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPackTest {

    @Test
    fun `게임 시작 시 카드팩은 56장으로 시작한다`() {
        val cardPack = CardPack()

        assertThat(cardPack.cards.size).isEqualTo(56)
    }

    @Test
    fun `한장을 뽑으면 카드 팩에서 한장 제거된다`() {
        val cardPack = CardPack()

        cardPack.draw()
        assertThat(cardPack.cards.size).isEqualTo(55)
    }

    @Test
    fun `카드팩 내의 카드가 0장이 되면 카드 덱 하나를 추가한다`() {
        val cardPack = CardPack()

        repeat(56) { cardPack.draw() }
        assertThat(cardPack.cards.size).isEqualTo(56)
    }
}
