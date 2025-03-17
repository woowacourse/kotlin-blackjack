package blackjack.domain.participant

import blackjack.domain.ACE_HEART
import blackjack.domain.SIX_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.hand.Hand
import blackjack.model.participant.Dealer
import blackjack.model.participant.Name
import blackjack.model.participant.Player
import blackjack.model.winning.WinningState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `이름을 가진 플레이어가 생성된다`() {
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

    @Test
    fun `플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 승리한다`() {
        // given
        val player =
            Player.create(
                Name("플레이어"),
                hand = Hand(listOf(ACE_HEART, TEN_HEART)),
            )
        val dealer =
            Dealer.create(
                Name("딜러"),
                hand = Hand(listOf(SIX_HEART, TEN_HEART)),
            )

        // when
        val result = player.winningState(dealer)

        // then
        assertEquals(WinningState.WIN_BY_BLACKJACK, result)
    }

    @Test
    fun `플레이어와 딜러가 블랙잭이면 무승부한다`() {
        // given
        val player =
            Player.create(
                Name("플레이어"),
                hand = Hand(listOf(ACE_HEART, TEN_HEART)),
            )
        val dealer =
            Dealer.create(
                Name("딜러"),
                hand = Hand(listOf(ACE_HEART, TEN_HEART)),
            )

        // when
        val result = player.winningState(dealer)

        // then
        assertEquals(WinningState.PUSH, result)
    }

    @Test
    fun `딜러가 블랙잭이면 플레이어는 패배한다`() {
        // given
        val player =
            Player.create(
                Name("플레이어"),
                hand = Hand(listOf(TEN_HEART)),
            )
        val dealer =
            Dealer.create(
                Name("딜러"),
                hand = Hand(listOf(ACE_HEART, TEN_HEART)),
            )

        // when
        val result = player.winningState(dealer)

        // then
        assertEquals(WinningState.LOSE, result)
    }

    @Test
    fun `플레이어가 버스트면 패배한다`() {
        // given
        val player =
            Player.create(
                Name("플레이어"),
                hand = Hand(listOf(TEN_HEART, TEN_HEART, TEN_HEART)),
            )
        val dealer =
            Dealer.create(Name("딜러"))

        // when
        val result = player.winningState(dealer)

        // then
        assertEquals(WinningState.LOSE, result)
    }

    @Test
    fun `플레이어가 생존하고 딜러가 버스트면 승리한다`() {
        // given
        val player =
            Player.create(Name("플레이어"))
        val dealer =
            Dealer.create(
                Name("딜러"),
                hand = Hand(listOf(TEN_HEART, TEN_HEART, TEN_HEART)),
            )

        // when
        val result = player.winningState(dealer)

        // then
        assertEquals(WinningState.WIN_DEFAULT, result)
    }

    @Test
    fun `플레이어와 딜러가 버스트면 플레이어가 패배한다`() {
        // given
        val player =
            Player.create(
                Name("플레이어"),
                hand = Hand(listOf(TEN_HEART, TEN_HEART, TEN_HEART)),
            )
        val dealer =
            Dealer.create(
                Name("딜러"),
                hand = Hand(listOf(TEN_HEART, TEN_HEART, TEN_HEART)),
            )

        // when
        val result = player.winningState(dealer)

        // then
        assertEquals(WinningState.LOSE, result)
    }
}
