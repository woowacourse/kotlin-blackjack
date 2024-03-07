package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `카드 덱에 Ace, Queen이 있을 때, 카드 점수는 21이다`() {
        val hand = Hand(listOf(Card(Pattern.HEART, CardNumber.ACE), Card(Pattern.HEART, CardNumber.QUEEN)))
        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
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
        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
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
        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
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
        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }
}
