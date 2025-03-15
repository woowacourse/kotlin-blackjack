package blackjack.domain.participant

import blackjack.domain.ACE_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.hand.Hand
import blackjack.model.participant.Name
import blackjack.model.participant.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 정상적으로 생성된다`() {
        // when
        val player = Player.create(Name("공백"))

        // then
        assertEquals("공백", player.name.value)
        assertEquals(Player.PLAYER_DEFAULT_MONEY, player.money)
        assertTrue(player.cards.isEmpty())
    }

    @Test
    fun `플레이어가 처음 2장의 카드를 공개한다`() {
        // given
        val hand =
            Hand(
                listOf(
                    TEN_HEART,
                    ACE_HEART,
                ),
            )
        val player = Player.create(name = Name("비비"), hand = hand)

        // when
        val initialCards = player.showInitialCards()

        // then
        assertEquals(2, initialCards.size)
        assertEquals(listOf(hand.cards[0], hand.cards[1]), initialCards)
    }

    @Test
    fun `플레이어가 HandState가 ALIVE일 때만 추가 카드를 뽑을 수 있다`() {
        // given
        val aliveHand =
            Hand(
                listOf(ACE_HEART, ACE_HEART),
            )
        val bustHand =
            Hand(
                listOf(TEN_HEART, TEN_HEART, TEN_HEART),
            )

        val alivePlayer = Player.create(name = Name("메다"), hand = aliveHand)
        val bustPlayer = Player.create(name = Name("제이"), hand = bustHand)

        // when & then
        assertTrue(alivePlayer.isDrawable)
        assertFalse(bustPlayer.isDrawable)
    }
}
