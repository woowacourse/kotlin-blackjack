package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `카드 덱에 Ace, Queen이 있을 때, 카드 점수는 21이다`() {
        val cards = Card.of(listOf(Pair(Pattern.HEART, CardNumber.ACE), Pair(Pattern.HEART, CardNumber.QUEEN)))

        val hand = Hand(cards)
        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }

    @Test
    fun `카드 덱에 Ace, Queen, King이 있을 때, 카드 점수는 21이다`() {
        val cards =
            Card.of(
                listOf(
                    Pair(Pattern.HEART, CardNumber.ACE),
                    Pair(Pattern.HEART, CardNumber.QUEEN),
                    Pair(Pattern.HEART, CardNumber.KING),
                ),
            )

        val hand = Hand(cards)

        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }

    @Test
    fun `카드 덱에 Ace 두 장과 9 한 장이 있을 때, 카드 점수는 21이다`() {
        val cards =
            Card.of(
                listOf(
                    Pair(Pattern.HEART, CardNumber.ACE),
                    Pair(Pattern.HEART, CardNumber.ACE),
                    Pair(Pattern.HEART, CardNumber.NINE),
                ),
            )

        val hand = Hand(cards)
        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }

    @Test
    fun `카드 덱에 Ace 두 장, King 한 장, 9 한 장이 있을 때, 카드 점수는 21이다`() {
        val cards =
            Card.of(
                listOf(
                    Pair(Pattern.HEART, CardNumber.ACE),
                    Pair(Pattern.HEART, CardNumber.ACE),
                    Pair(Pattern.SPADE, CardNumber.KING),
                    Pair(Pattern.HEART, CardNumber.NINE),
                ),
            )

        val hand = Hand(cards)

        Assertions.assertThat(hand.calculate()).isEqualTo(Hand.BLACKJACK_NUMBER)
    }
}
