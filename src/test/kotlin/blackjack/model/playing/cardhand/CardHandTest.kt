package blackjack.model.playing.cardhand

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardHandTest {
    // 딜러나 플레이어 카드 패의 합을 구하는 동작은 완전히 같다.
    private val explicitCardDeck =
        listOf(
            Card(CardShape.CLOVER, CardNumber.FIVE),
            Card(CardShape.DIAMOND, CardNumber.THREE),
            Card(CardShape.HEART, CardNumber.SEVEN),
            Card(CardShape.SPADE, CardNumber.SIX),
            Card(CardShape.DIAMOND, CardNumber.FOUR),
            Card(CardShape.CLOVER, CardNumber.EIGHT),
            Card(CardShape.SPADE, CardNumber.TWO),
            Card(CardShape.HEART, CardNumber.NINE),
        )

    @Test
    fun `숫자 카드는 각 숫자에 해당하는 점수로 계산한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.CLOVER, CardNumber.QUEEN),
            )

        assertThat(cardHand.sum()).isEqualTo(24)
    }

    @Test
    fun `카드 패의 ACE 가 없을 때 카드의 합을 계산한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.SPADE, CardNumber.SEVEN),
                Card(CardShape.HEART, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.FIVE),
            )

        assertThat(cardHand.sum()).isEqualTo(18)
    }

    @Test
    fun `A 카드가 포함되어 있으면 10점을 추가로 획득한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.SPADE, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.ACE),
            )

        assertThat(cardHand.sum()).isEqualTo(12)
    }

    @Test
    fun `카드 패의 ACE 가 세 장 있을 때 한 장만 11이 된다`() {
        val cardHand =
            CardHand(
                Card(CardShape.SPADE, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.ACE),
            )

        assertThat(cardHand.sum()).isEqualTo(13)
    }

    @Test
    fun `A 카드가 포함되어 있을 때, 10점을 추가해서 21점을 초과한다면, 10점을 추가하지 않는다`() {
        val cardHand =
            mutableListOf(
                Card(CardShape.SPADE, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.NINE),
            )
        val currentCardHand = CardHand(cardHand + Card(CardShape.HEART, CardNumber.TEN))

        val actual = currentCardHand.sum()
        assertThat(actual).isEqualTo(20)
    }

    @Test
    fun `J, Q, K 카드는 10점으로 계산한다`() {
        val hand =
            mutableListOf(
                Card(CardShape.SPADE, CardNumber.JACK),
                Card(CardShape.HEART, CardNumber.QUEEN),
            )
        assertThat(CardHand(hand).sum()).isEqualTo(20)
    }
}
