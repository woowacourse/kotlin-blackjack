package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealingShoeTest {
    private val twoHeartCard = Card(CardNumber.TWO, Suit.HEART)
    private val threeHeartCard = Card(CardNumber.THREE, Suit.HEART)
    private val fourHeartCard = Card(CardNumber.FOUR, Suit.HEART)
    private val fiveHeartCard = Card(CardNumber.FIVE, Suit.HEART)
    private lateinit var dealingShoe: DealingShoe

    @Test
    fun `2하트만 있을 때 카드를 뽑으면 2하트가 나와야 한다`() {
        dealingShoe = DealingShoe(listOf(twoHeartCard))
        val actual = dealingShoe.pickCard()
        assertThat(actual).isEqualTo(twoHeartCard)
    }

    @Test
    fun `2하트, 3하트, 4하트 순서로 있을 때, 카드를 뽑으면 맨 앞의 2하트부터 나와야 한다`() {
        dealingShoe = DealingShoe(listOf(twoHeartCard, threeHeartCard, fourHeartCard))
        val actual = dealingShoe.pickCard()
        assertThat(actual).isEqualTo(twoHeartCard)
    }

    @Test
    fun `4장의 카드가 있을 때, 카드를 5번 뽑으면 예외를 던진다`() {
        dealingShoe = DealingShoe(listOf(twoHeartCard, threeHeartCard, fourHeartCard, fiveHeartCard))
        val cardSize = 4
        val exception =
            assertThrows<IllegalStateException> {
                repeat(cardSize + 1) {
                    dealingShoe.pickCard()
                }
            }.message
        assertThat(exception).isEqualTo("모든 카드를 소진해 게임을 종료합니다.")
    }
}
