package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardFactoryTest {
    private lateinit var factory: CardFactory

    @BeforeEach
    fun setUp() {
        factory = CardFactoryImpl()
    }

    @Test
    fun `카드는 총 52장이 생성된다`() {
        val cards = factory.makeCard()
        assertThat(cards.size).isEqualTo(52)
    }

    @Test
    fun `카드 뭉치엔 중복된 카드가 존재하지 않는다`() {
        val card = factory.makeCard()
        val distinctCard = card.distinct()

        assertThat(card.size).isEqualTo(distinctCard.size)
    }
}
