package domain.state

import domain.Dummy
import domain.Dummy.CLOVER_ACE
import domain.Dummy.CLOVER_FIVE
import domain.Dummy.CLOVER_FOUR
import domain.Dummy.CLOVER_KING
import domain.Dummy.CLOVER_TEN
import domain.Dummy.CLOVER_THREE
import domain.Dummy.CLOVER_TWO
import domain.card.HandOfCards
import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StayTest {
    @Test
    fun `toStay함수를 호출하면 StateException이 발생한다`() {
        val stay = Stay(HandOfCards(CLOVER_KING, CLOVER_TWO))

        assertThrows<IllegalStateException> { stay.toStay() }
            .shouldHaveMessage("이미 끝났습니다. stay를 호출할 수 없습니다.")
    }

    @Test
    fun `nextState함수를 호출하면 StateException이 발생한다`() {
        val stay = Stay(HandOfCards(CLOVER_KING, CLOVER_TWO))

        assertThrows<IllegalStateException> { stay.nextState { Dummy.CLOVER_TWO } }
            .shouldHaveMessage("이미 끝났습니다. 다음 State가 없습니다.")
    }

    @Test
    fun `플레이어가 스테이이고 딜러가 블랙잭일때 플레이어의 수익은 베팅금액의 -1배이다`() {
        val playerState = Stay(HandOfCards(CLOVER_TWO, CLOVER_ACE))
        val otherState = BlackJack(HandOfCards(CLOVER_KING, CLOVER_ACE))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(-1000.0)
    }

    @Test
    fun `플레이어가 스테이이고 딜러가 버스트일때 플레이어의 수익은 배팅금액만큼이다`() {
        val playerState = Stay(HandOfCards(CLOVER_TWO, CLOVER_ACE))
        val otherState = Bust(HandOfCards(CLOVER_KING, CLOVER_TEN).apply { addCard(CLOVER_TWO) })

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(1000.0)
    }

    @Test
    fun `모두 스테이이고 플레이어의 카드합이 더 클때 플레이어의 수익은 베팅금액만큼이다`() {
        val playerState = Stay(HandOfCards(CLOVER_TWO, CLOVER_ACE))
        val otherState = Stay(HandOfCards(CLOVER_TWO, CLOVER_THREE))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(1000.0)
    }

    @Test
    fun `모두 스테이이고 딜러의 카드합이 더 클때 플레이어의 수익은 베팅금액의 -1배이다`() {
        val playerState = Stay(HandOfCards(CLOVER_TWO, CLOVER_ACE))
        val otherState = Stay(HandOfCards(CLOVER_TEN, CLOVER_FOUR))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(-1000.0)
    }

    @Test
    fun `모두 스테이이고 카드합이 같을 때 플레이어의 수익은 0이다`() {
        val playerState = Stay(HandOfCards(CLOVER_TWO, CLOVER_FIVE))
        val otherState = Stay(HandOfCards(CLOVER_THREE, CLOVER_FOUR))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(0.0)
    }
}
