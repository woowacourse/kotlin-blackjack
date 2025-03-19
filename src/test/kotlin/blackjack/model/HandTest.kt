package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `카드들을 생성자로 카드 리스트를 받는다`() {
        val cards = listOf((Card(Suit.HEART, Denomination.FIVE)), Card(Suit.CLOVER, Denomination.TWO))
        val actual = Hand(cards)

        assertThat(actual.value).isEqualTo(cards)
    }

    @Test
    fun `Ace 카드가 1~4개가 아닐 때 일때, 카드들의 점수를 반환한다`() {
        val cards = listOf((Card(Suit.HEART, Denomination.FIVE)), Card(Suit.CLOVER, Denomination.TWO))
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(7)
    }

    @Test
    fun `Ace 카드가 1개이고 카드들의 점수가 11미만 일때, Ace의 점수를 11로 판단하고, 카드의 스코어에 Ace의 점수를 추가하여 반환한다`() {
        val cards = listOf((Card(Suit.HEART, Denomination.ACE)), Card(Suit.CLOVER, Denomination.KING))
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `Ace 카드가 1개이고 카드들의 점수가 11이상 일때, Ace의 점수를 1로 판단하고, 카드의 스코어에 Ace의 점수를 추가하여 반환한다`() {
        val cards = listOf((Card(Suit.HEART, Denomination.ACE)), Card(Suit.CLOVER, Denomination.TWO))
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `Ace 카드가 2개이고 카드들의 점수가 10미만 일때, Ace둘의 점수를 12로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.THREE),
                Card(Suit.DIAMOND, Denomination.ACE),
            )
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(15)
    }

    @Test
    fun `Ace 카드가 2개이고 카드들의 점수가 10이상 일때, Ace들의 점수를 2로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.TEN),
                Card(Suit.DIAMOND, Denomination.ACE),
            )
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `Ace 카드가 3개이고 카드들의 점수가 9미만 일때, Ace둘의 점수를 13로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.THREE),
                Card(Suit.DIAMOND, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.ACE),
            )
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(16)
    }

    @Test
    fun `Ace 카드가 3개이고 카드들의 점수가 9이상 일때, Ace들의 점수를 3로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.NINE),
                Card(Suit.DIAMOND, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.ACE),
            )
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `Ace 카드가 4개이고 카드들의 점수가 8미만 일때, Ace둘의 점수를 14로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.THREE),
                Card(Suit.DIAMOND, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
            )
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(17)
    }

    @Test
    fun `Ace 카드가 4개이고 카드들의 점수가 8이상 일때, Ace들의 점수를 4로 판단하고, 카드의 스코어에 Ace들의 점수를 추가하여 반환한다`() {
        val cards =
            listOf(
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.NINE),
                Card(Suit.DIAMOND, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
            )
        val actual = Hand(cards).getScore()

        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `카드들에 카드를 추가한다`() {
        val hand =
            Hand(
                listOf(
                    Card(Suit.HEART, Denomination.ACE),
                    Card(Suit.CLOVER, Denomination.NINE),
                ),
            )
        val addCard = Card(Suit.CLOVER, Denomination.TWO)
        hand.add(addCard)

        assertThat(hand.value).isEqualTo(
            listOf(
                Card(Suit.HEART, Denomination.ACE),
                Card(Suit.CLOVER, Denomination.NINE),
                Card(Suit.CLOVER, Denomination.TWO),
            ),
        )
    }

    @Test
    fun `내 카드가 블랙잭이고, 상대가 블랙잭이 아니면 승리를 반환한다`() {
        val playerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.ACE), Card(Suit.CLOVER, Denomination.TEN)))
        val dealerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.TEN), Card(Suit.CLOVER, Denomination.NINE)))

        val result = playerHand.gameResult(dealerHand)

        assertThat(result).isEqualTo(GameResult.BLACKJACK_WIN)
    }

    @Test
    fun `플레이어가 버스트하면 패배를 반환한다`() {
        val playerHand =
            Hand(
                listOf(
                    Card(Suit.CLOVER, Denomination.TEN),
                    Card(Suit.DIAMOND, Denomination.TEN),
                    Card(Suit.CLOVER, Denomination.EIGHT),
                ),
            )
        val dealerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.TEN), Card(Suit.CLOVER, Denomination.SIX)))

        val result = playerHand.gameResult(dealerHand)

        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `상대가 버스트하고, 내가 버스트가 아니면 승리를 반환한다`() {
        val playerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.TEN), Card(Suit.CLOVER, Denomination.SIX)))
        val dealerHand =
            Hand(
                listOf(
                    Card(Suit.CLOVER, Denomination.TEN),
                    Card(Suit.CLOVER, Denomination.SIX),
                    Card(Suit.DIAMOND, Denomination.TEN),
                ),
            )

        val result = playerHand.gameResult(dealerHand)

        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `내 점수가 상대보다 높으면 승리를 반환한다`() {
        val playerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.TEN), Card(Suit.CLOVER, Denomination.SIX)))
        val dealerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.NINE), Card(Suit.CLOVER, Denomination.SIX)))

        val result = playerHand.gameResult(dealerHand)

        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `내 점수가 상대보다 낮으면 패배를 반환한다`() {
        val playerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.TEN), Card(Suit.CLOVER, Denomination.FOUR)))
        val dealerHand = Hand(listOf(Card(Suit.CLOVER, Denomination.QUEEN), Card(Suit.CLOVER, Denomination.FIVE)))

        val result = playerHand.gameResult(dealerHand)

        assertThat(result).isEqualTo(GameResult.LOSE)
    }
}
