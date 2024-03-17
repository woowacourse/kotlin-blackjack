package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `플레이어만 bust될 때 무조건 패배한다`() {
        val dealer = buildDealer(four)

        val player = buildPlayer("a", ten, ten, ten)

        val actual = player versus dealer
        val expected = GameResult.Lose
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러와 플레이어가 둘 다 bust될 때 플레이어가 패배한다`() {
        val dealer = buildDealer(ten, ten, ten)

        val player = buildPlayer("a", ten, ten, ten)

        val actual = player versus dealer
        val expected = GameResult.Lose
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `둘 다 블랙잭인 경우 무승부로 한다`() {
        val dealer = buildDealer(ten, ace)

        val player = buildPlayer("a", ten, ace)

        val actual = player versus dealer
        val expected = GameResult.Draw
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어가 21인 경우 패배한다`() {
        val dealer = buildDealer(ten, ace)

        val player = buildPlayer("a", ten, five, six)

        val actual = player versus dealer
        val expected = GameResult.Lose
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러가 21인 경우 승리한다`() {
        val dealer = buildDealer(ten, five, six)

        val player = buildPlayer("a", ten, ace)

        val actual = player versus dealer
        val expected = GameResult.Win
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `모두 블랙잭이 아니고 플레이어의 점수가 클 경우 플레이어가 승리한다`() {
        val dealer = buildDealer(two, three)

        val player = buildPlayer("a", four, five)

        val actual = player versus dealer
        val expected = GameResult.Win
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `모두 블랙잭이 아니고 플레이어의 점수가 작을 경우 플레이어가 패배한다`() {
        val dealer = buildDealer(four, five)

        val player = buildPlayer("a", two, three)

        val actual = player versus dealer
        val expected = GameResult.Lose
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `모두 블랙잭이 아니고 점수가 같을 경우 무승부로 한다`() {
        val dealer = buildDealer(two, three)

        val player = buildPlayer("a", two, three)

        val actual = player versus dealer
        val expected = GameResult.Draw
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러가 버스트 된 경우 플레이어가 승리한다`() {
        val dealer = buildDealer(six, ten, nine)

        val player = buildPlayer("a", five, seven, six)

        val actual = player versus dealer
        val expected = GameResult.Win
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
