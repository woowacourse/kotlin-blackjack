package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `플레이어가 쥔 카드들의 숫자 합을 계산한다`() {
        val cardList = listOf(
            Card(Pattern.CLOVER, CardNumber.FIVE),
            Card(Pattern.SPADE, CardNumber.TEN),
            )
        val hand = Hand(cardList)
        assertThat(hand.calculate()).isEqualTo(15)
    }

    @Test
    fun `카드 덱에 Ace, Queen이 있을 때, 카드 점수는 21이다`() {
        val hand = Hand(listOf(Card(Pattern.HEART, CardNumber.ACE), Card(Pattern.HEART, CardNumber.QUEEN)))
        assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }

    @Test
    fun `카드 덱에 Ace, Queen, King이 있을 때, 카드 점수는 21이다`() {
        val hand =
            Hand(
                listOf(
                    Card(Pattern.HEART, CardNumber.ACE),
                    Card(Pattern.HEART, CardNumber.QUEEN),
                    Card(Pattern.HEART, CardNumber.KING),
                ),
            )
        assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }

    @Test
    fun `카드 덱에 Ace 두 장과 9 한 장이 있을 때, 카드 점수는 21이다`() {
        val hand =
            Hand(
                listOf(
                    Card(Pattern.HEART, CardNumber.ACE),
                    Card(Pattern.SPADE, CardNumber.ACE),
                    Card(Pattern.HEART, CardNumber.NINE),
                ),
            )
        assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }

    @Test
    fun `카드 덱에 Ace 두 장, King 한 장, 9 한 장이 있을 때, 카드 점수는 21이다`() {
        val hand =
            Hand(
                listOf(
                    Card(Pattern.HEART, CardNumber.ACE),
                    Card(Pattern.SPADE, CardNumber.ACE),
                    Card(Pattern.SPADE, CardNumber.KING),
                    Card(Pattern.HEART, CardNumber.NINE),
                ),
            )
        assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }
}
