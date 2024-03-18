package model

import fixture.SPADE_ACE
import fixture.SPADE_KING
import fixture.SPADE_TEN
import fixture.SPADE_THREE
import fixture.SPADE_TWO
import model.human.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun handInit() {
        hand = Hand()
    }

    @Test
    fun `핸드의 합이 21 미만인 경우, 카드를 더 받을 수 있다`() {
        val player = Player(hand)

        player.hand.add(SPADE_TEN)
        player.hand.add(SPADE_KING)

        assertThat(player.isHittable()).isTrue
    }

    @Test
    fun `핸드의 합이 21 이상인 경우, 카드를 더 받을 수 없다`() {
        val player = Player(hand)

        player.hand.add(SPADE_TEN)
        player.hand.add(SPADE_KING)
        player.hand.add(SPADE_TWO)

        assertThat(player.isHittable()).isFalse
    }

    @Test
    fun `핸드의 합이 21 초과인 경우 버스트가 된다`() {
        hand.add(SPADE_TEN)
        hand.add(SPADE_KING)
        hand.add(SPADE_THREE)

        val player = Player(hand)

        assertThat(player.isBusted()).isTrue
    }

    @Test
    fun `블랙잭 판단을 할 수 있다`() {
        hand.add(SPADE_TEN)
        hand.add(SPADE_ACE)

        val player = Player(hand)
        assertThat(player.isBlackJack()).isTrue
    }
}
