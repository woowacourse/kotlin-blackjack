package entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    private fun generateCardsByNumber(number: Int): Cards {
        val cards = mutableListOf<Card>()
        var remain = number
        while (remain > 0) {
            remain -= addCardByNumber(remain, cards)
        }
        return Cards(cards)
    }

    private fun addCardByNumber(remain: Int, cards: MutableList<Card>): Int {
        return if (remain > 10) {
            cards.add(Card(CardType.SPADE, CardNumber.TEN))
            10
        } else {
            cards.add(Card(CardType.SPADE, CardNumber.values()[remain - 1]))
            remain
        }
    }

    @Test
    fun `플레이어가 가진 카드의 숫자 합이 21 미만이면 한장의 카드를 더 받을 수 있다`() {
        val dealer = Dealer(generateCardsByNumber(10))
        val isDistributable = dealer.isDistributable()

        assertThat(isDistributable).isTrue
    }

    @Test
    fun `플레이어가 가진 카드의 숫자 합이 21 이상이면 한장의 카드를 더 받을 수 없다`() {
        val dealer = Dealer(generateCardsByNumber(22))

        val isDistributable = dealer.isDistributable()

        assertThat(isDistributable).isFalse
    }

    @Test
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 21이면 무승부이다`() {
        // given
        val player1 = Player(
            Name("test"), generateCardsByNumber(21)
        )
        val results = player1.determineGameResult(21)

        // when
        val except = player1 to GameResultType.DRAW

        // then
        assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 22이면 무승부이다`() {
        // given
        val player1 = Player(
            Name("test"), generateCardsByNumber(22)
        )
        val results = player1.determineGameResult(22)

        // when
        val except = player1 to GameResultType.DRAW

        // then
        assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 19이고 딜러 카드 숫자의 합이 22이면 플레이어의 승리이다`() {
        // given
        val player1 = Player(
            Name("test"), generateCardsByNumber(19)
        )

        val results = player1.determineGameResult(22)

        // when
        val except = player1 to GameResultType.WIN

        // then
        assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 20이면 플레이어의 승리이다`() {
        // given
        val player1 = Player(
            Name("test"), generateCardsByNumber(21)
        )
        val results = player1.determineGameResult(20)

        // when
        val except = player1 to GameResultType.WIN

        // then
        assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 20이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val player1 = Player(
            Name("test"), generateCardsByNumber(20)
        )
        val results = player1.determineGameResult(21)

        // when
        val except = player1 to GameResultType.LOSE

        // then
        assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val player1 = Player(
            Name("test"), generateCardsByNumber(22)
        )
        val results = player1.determineGameResult(21)

        // when
        val except = player1 to GameResultType.LOSE

        // then
        assertThat(results).isEqualTo(except)
    }
}
