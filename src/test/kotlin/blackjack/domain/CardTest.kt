package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardTest {
    @Test
    fun `카드는 끗수가 Ace이면 1점이다`() {
        val card = Card(Suit.CLUB, Denomination.ACE)
        assertThat(card.denomination.value).isEqualTo(1)
    }

    @Test
    fun `카드는 끗수가 Jack이면 10점이다`() {
        val card = Card(Suit.CLUB, Denomination.JACK)
        assertThat(card.denomination.value).isEqualTo(10)
    }

    @Test
    fun `카드는 끗수가 Queen이면 10점이다`() {
        val card = Card(Suit.CLUB, Denomination.QUEEN)
        assertThat(card.denomination.value).isEqualTo(10)
    }

    @Test
    fun `카드는 끗수가 King이면 10점이다`() {
        val card = Card(Suit.CLUB, Denomination.KING)
        assertThat(card.denomination.value).isEqualTo(10)
    }

    @ParameterizedTest(name = "카드 {0}은(는) 점수 {0}을(를) 가져야 한다")
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `카드는 끗수가 숫자면 숫자를 점수로 갖는다`(int: Int) {
        val denominationMap = Denomination.entries.associateBy { it.value }
        val card = Card(Suit.CLUB, denominationMap.getOrDefault(int, Denomination.ACE))
        assertThat(card.denomination.value).isEqualTo(int)
    }
}
