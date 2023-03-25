package domain.player

import model.domain.player.User
import model.tools.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `산군은 5000원을 배팅했다`() {
        // given
        val name = "산군"
        val bettingMoney = Money(5000)
        val user = User.from(name)

        // when
        user.betMoney(bettingMoney)
        val actual = user.money.amount

        // then
        assertThat(actual).isEqualTo(5000)
    }

    @Test
    fun `산군은 게임에서 졌기에, 배팅금액 5천원을 잃었다`() {
        // given
        val name = "산군"
        val bettingMoney = Money(5000)
        val user = User.from(name)
        user.betMoney(bettingMoney)

        // when
        user.lose()
        val actual = user.money.amount

        // then
        assertThat(actual).isEqualTo(-5000)
    }

    @Test
    fun `산군은 게임에서 비겼기에, 배팅금액을 돌려받았다`() {
        // given
        val name = "산군"
        val bettingMoney = Money(5000)
        val user = User.from(name)
        user.betMoney(bettingMoney)

        // when
        user.draw()
        val actual = user.money.amount

        // then
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `산군은 게임에서 블랙잭이기에, 배팅금액의 150%를 받는다`() {
        // given
        val name = "산군"
        val bettingMoney = Money(5000)
        val user = User.from(name)
        user.betMoney(bettingMoney)

        println(user.money.amount)

        // when
        user.winnerWinnerChickenDinner()
        println(user.money.amount)
        val actual = user.money.amount

        // then
        assertThat(actual).isEqualTo(2500)
    }

    @Test
    fun `산군은 게임에서 승리해, 배팅금액의 200%를 받는다`() {
        // given
        val name = "산군"
        val bettingMoney = Money(5000)
        val user = User.from(name)
        user.betMoney(bettingMoney)

        // when
        user.win()
        val actual = user.money.amount

        // then
        assertThat(actual).isEqualTo(5000)
    }
}
