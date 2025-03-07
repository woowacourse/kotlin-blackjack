package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드들을 생성자로 카드 리스트를 받는다`() {
        val cards = listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO))
        val actual = Cards(cards)

        assertThat(actual.value).isEqualTo(cards)
    }

    @Test
    fun `Ace 카드가 1~4개가 아닐 때 일때, 카드들의 점수를 반환한다`() {
        val cards = listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO))
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(7)
    }

    @Test
    fun `Ace 카드가 1개이고 카드들의 점수가 11미만 일때, Ace의 점수를 11로 판단하고, 카드의 스코어에 Ace의 점수를 추가하여 반환한다`() {
        val cards = listOf((Card(CardShape.HEART, Denomination.ACE)), Card(CardShape.CLOVER, Denomination.KING))
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `Ace 카드가 1개이고 카드들의 점수가 11이상 일때, Ace의 점수를 1로 판단하고, 카드의 스코어에 Ace의 점수를 추가하여 반환한다`() {
        val cards = listOf((Card(CardShape.HEART, Denomination.ACE)), Card(CardShape.CLOVER, Denomination.TWO))
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `Ace 카드가 2개이고 카드들의 점수가 10미만 일때, Ace둘의 점수를 12로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(CardShape.HEART, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.THREE),
                Card(CardShape.DIAMOND, Denomination.ACE),
            )
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(15)
    }

    @Test
    fun `Ace 카드가 2개이고 카드들의 점수가 10이상 일때, Ace들의 점수를 2로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(CardShape.HEART, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.TEN),
                Card(CardShape.DIAMOND, Denomination.ACE),
            )
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `Ace 카드가 3개이고 카드들의 점수가 9미만 일때, Ace둘의 점수를 13로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(CardShape.HEART, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.THREE),
                Card(CardShape.DIAMOND, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.ACE),
            )
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(16)
    }

    @Test
    fun `Ace 카드가 3개이고 카드들의 점수가 9이상 일때, Ace들의 점수를 3로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(CardShape.HEART, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.NINE),
                Card(CardShape.DIAMOND, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.ACE),
            )
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `Ace 카드가 4개이고 카드들의 점수가 8미만 일때, Ace둘의 점수를 14로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(CardShape.HEART, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.THREE),
                Card(CardShape.DIAMOND, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.ACE),
                Card(CardShape.SPADE, Denomination.ACE),
            )
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(17)
    }

    @Test
    fun `Ace 카드가 4개이고 카드들의 점수가 8이상 일때, Ace들의 점수를 4로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(CardShape.HEART, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.NINE),
                Card(CardShape.DIAMOND, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.ACE),
                Card(CardShape.SPADE, Denomination.ACE),
            )
        val actual = Cards(cards).calculateScore()

        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `카드들에 카드를 추가한다`() {
        val cards =
            Cards(
                listOf(
                    Card(CardShape.HEART, Denomination.ACE),
                    Card(CardShape.CLOVER, Denomination.NINE),
                ),
            )
        val addCard = Card(CardShape.CLOVER, Denomination.TWO)
        cards.add(addCard)

        assertThat(cards.value).isEqualTo(
            listOf(
                Card(CardShape.HEART, Denomination.ACE),
                Card(CardShape.CLOVER, Denomination.NINE),
                Card(CardShape.CLOVER, Denomination.TWO),
            ),
        )
    }
}
