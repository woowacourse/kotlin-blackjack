package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import domain.card.Cards
import domain.participant.BettingMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `드로우했을때 burst면 burst 상태를 반환한다`() {
        val hit = Hit(Cards(10, 10), BettingMoney(1000))
        val actual = hit.draw(Card(10))
        assertThat(actual).isInstanceOf(Burst::class.java)
    }

    @Test
    fun `드로우했을때 Hit면 Hit 상태를 반환한다`() {
        val hit = Hit(Cards(2, 3), BettingMoney(1000))
        val actual = hit.draw(Card(10))
        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `두번 드로우했을때 두번 모두 Hit면 Hit 상태를 반환한다`() {
        val hit = Hit(Cards(2, 3), BettingMoney(1000))
        val actual = hit.draw(Card(10)).draw(Card(5))
        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `드로우했을때 처음은 Hit고 두번째 Burst면 burst 상태를 반환한다`() {
        val hit = Hit(Cards(2, 3), BettingMoney(1000))
        val actual = hit.draw(Card(10)).draw(Card(10))
        assertThat(actual).isInstanceOf(Burst::class.java)
    }

    @Test
    fun `드로우를 그만하고 싶으면 stay상태를 반환한다`() {
        val hit = Hit(Cards(2, 3), BettingMoney(1000))
        val actual = hit.stay()
        assertThat(actual).isInstanceOf(Stay::class.java)
    }

    private fun Card(number: Int): Card {
        return Card.of(CardCategory.CLOVER, CardNumber.values().find { it.value == number } ?: CardNumber.FIVE)
    }

    private fun Cards(vararg cards: Int): Cards {
        return Cards(
            cards.map { number ->
                Card(number)
            },
        )
    }
}
