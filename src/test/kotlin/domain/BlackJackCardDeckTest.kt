package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackCardDeckTest {

    @Test
    fun `캐싱된 카드뭉치에서 하나를 제거하면 그 카드가 존재하지 않는다`() {
        val cachingCards = BlackJackCardDeck()
        val curSize = cachingCards.size
        val popCard = cachingCards.draw()
        val result = cachingCards.contains(popCard!!)
        assertThat(result).isFalse
        assertThat(cachingCards.size).isEqualTo(curSize - 1)
    }
}
