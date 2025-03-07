package blackjack

import blackjack.model.Card
import blackjack.model.Number
import blackjack.model.Person
import blackjack.model.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PersonTest {
    private lateinit var person: Person

    @BeforeEach
    fun setUp() {
        person =
            object : Person() {
                override fun isBust(): Boolean {
                    return true
                }
            }
    }

    @Test
    fun `참여자는 카드 한 장을 받을 수 있다`() {
        person.addCard(Card(Shape.SPADE, Number.NINE))
        assertThat(person.cards.size).isEqualTo(1)
    }

    @Test
    fun `참여자 카드의 총 합을 계산한다`() {
        person.addCard(Card(Shape.SPADE, Number.NINE))
        person.addCard(Card(Shape.SPADE, Number.SEVEN))
        val expect = 16
        val actual = person.calculateTotalScore()

        assertThat(actual).isEqualTo(expect)
    }
}
