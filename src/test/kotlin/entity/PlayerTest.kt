package entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 가진 카드의 숫자 합이 21 미만이면 한장의 카드를 더 받을 수 있다`() {
        // given
        val dealer = Dealer(generateCardsByNumber(10))

        // when
        val actual = dealer.isDistributable()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `플레이어가 가진 카드의 숫자 합이 21 이상이면 한장의 카드를 더 받을 수 없다`() {
        // given
        val dealer = Dealer(generateCardsByNumber(22))

        // when
        val actual = dealer.isDistributable()

        // then
        assertThat(actual).isFalse
    }

    @Test
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 21이면 무승부이다`() {
        // given
        val player1 = Player(
            Name("test"), Money(0), generateCardsByNumber(21)
        )

        // when
        val actual = player1.determineGameResult(21)

        // then
        val except = GameResultType.DRAW
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 22이면 무승부이다`() {
        // given
        val player1 = Player(
            Name("test"), Money(0), generateCardsByNumber(22)
        )

        // when
        val actual = player1.determineGameResult(22)

        // then
        val except = GameResultType.DRAW
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 19이고 딜러 카드 숫자의 합이 22이면 플레이어의 승리이다`() {
        // given
        val player1 = Player(
            Name("test"), Money(0), generateCardsByNumber(19)
        )

        // when
        val actual = player1.determineGameResult(22)

        // then
        val except = GameResultType.WIN
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 20이면 플레이어의 승리이다`() {
        // given
        val player1 = Player(
            Name("test"), Money(0), generateCardsByNumber(21)
        )

        // when
        val actual = player1.determineGameResult(20)

        // then
        val except = GameResultType.WIN
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 20이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val player1 = Player(
            Name("test"), Money(0), generateCardsByNumber(20)
        )

        // when
        val actual = player1.determineGameResult(21)

        // then
        val except = GameResultType.LOSE
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val player1 = Player(
            Name("test"), Money(0), generateCardsByNumber(22)
        )

        // when
        val actual = player1.determineGameResult(21)

        // then
        val except = GameResultType.LOSE
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `1000원을 배팅하고 플레이어가 승리할 시 수익은 2000원이 된다`() {
        // given
        val player1 = Player(Name("test"), Money(1000))

        // when
        val actual = player1.calculateWinMoney(GameResultType.WIN)

        // then
        val except = Money(2000)
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `1000원을 배팅하고 플레이어가 승리하고 블랙잭일 때 수익은 1500원이 된다`() {
        // given
        val player1 = Player(
            Name("test"),
            Money(1000),
            Cards(listOf(Card(CardType.SPADE, CardNumber.ACE), Card(CardType.SPADE, CardNumber.TEN)))
        )

        // when
        val actual = player1.calculateWinMoney(GameResultType.WIN)

        // then
        val except = Money(1500)
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `1000원을 배팅하고 플레이어가 패배할 시 수익은 -1000원이 된다`() {
        // given
        val player1 = Player(Name("test"), Money(1000))

        // when
        val actual = player1.calculateWinMoney(GameResultType.LOSE)

        // then
        val except = Money(-1000)
        assertThat(actual).isEqualTo(except)
    }

    @Test
    fun `1000원을 배팅하고 플레이어가 무승부일시 수익은 0원이 된다`() {
        // given
        val player1 = Player(Name("test"), Money(1000))

        // when
        val actual = player1.calculateWinMoney(GameResultType.DRAW)

        // then
        val except = Money(0)
        assertThat(actual).isEqualTo(except)
    }

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
}
