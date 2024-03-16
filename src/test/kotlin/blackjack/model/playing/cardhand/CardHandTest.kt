package blackjack.model.playing.cardhand

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardHandTest {
    // 딜러나 플레이어 카드 패의 합을 구하는 동작은 완전히 같다.
    private val explicitCardDeck =
        listOf(
            Card(Suit.CLOVER, Denomination.FIVE),
            Card(Suit.DIAMOND, Denomination.THREE),
            Card(Suit.HEART, Denomination.SEVEN),
            Card(Suit.SPADE, Denomination.SIX),
            Card(Suit.DIAMOND, Denomination.FOUR),
            Card(Suit.CLOVER, Denomination.EIGHT),
            Card(Suit.SPADE, Denomination.TWO),
            Card(Suit.HEART, Denomination.NINE),
        )

    @Test
    fun `숫자 카드는 각 숫자에 해당하는 점수로 계산한다`() {
        val cardHand =
            CardHand(
                Card(Suit.CLOVER, Denomination.ACE),
                Card(Suit.HEART, Denomination.SEVEN),
                Card(Suit.SPADE, Denomination.SIX),
                Card(Suit.CLOVER, Denomination.QUEEN),
            )

        assertThat(cardHand.calculateScore()).isEqualTo(24)
    }

    @Test
    fun `카드 패의 ACE 가 없을 때 카드의 합을 계산한다`() {
        val cardHand =
            CardHand(
                Card(Suit.SPADE, Denomination.SEVEN),
                Card(Suit.HEART, Denomination.SIX),
                Card(Suit.HEART, Denomination.FIVE),
            )

        assertThat(cardHand.calculateScore()).isEqualTo(18)
    }

    @Test
    fun `A 카드가 포함되어 있으면 10점을 추가로 획득한다`() {
        val cardHand =
            CardHand(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.HEART, Denomination.ACE),
            )

        assertThat(cardHand.calculateScore()).isEqualTo(12)
    }

    @Test
    fun `카드 패의 ACE 가 세 장 있을 때 한 장만 11이 된다`() {
        val cardHand =
            CardHand(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.HEART, Denomination.ACE),
            )

        assertThat(cardHand.calculateScore()).isEqualTo(13)
    }

    @Test
    fun `A 카드가 포함되어 있을 때, 10점을 추가해서 21점을 초과한다면, 10점을 추가하지 않는다`() {
        val cardHand =
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.HEART, Denomination.NINE),
            )
        val currentCardHand = CardHand(cardHand + Card(Suit.HEART, Denomination.TEN))

        val actual = currentCardHand.calculateScore()
        assertThat(actual).isEqualTo(20)
    }

    @Test
    fun `J, Q, K 카드는 10점으로 계산한다`() {
        val hand =
            mutableListOf(
                Card(Suit.SPADE, Denomination.JACK),
                Card(Suit.HEART, Denomination.QUEEN),
            )
        assertThat(CardHand(hand).calculateScore()).isEqualTo(20)
    }
}
