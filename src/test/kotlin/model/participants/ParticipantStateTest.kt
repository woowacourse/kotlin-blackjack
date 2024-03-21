package model.participants

import model.HEART_ACE
import model.HEART_JACK
import model.HEART_SIX
import model.HEART_TEN
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantStateTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setUp() {
        hand = Hand()
    }

    @Test
    fun `None 상태에서 카드 한 장을 받으면 Ready 상태로`() {
        val participantState = ParticipantState.None()

        assertTrue(participantState.hit(HEART_ACE) is ParticipantState.Ready)
    }

    @Test
    fun `Ready 상태에서 카드 한 장을 받고, 합이 21이면 BlackJack 상태로`() {
        hand.draw(HEART_ACE)
        val participantState = ParticipantState.Ready(hand)

        assertTrue(participantState.hit(HEART_JACK) is ParticipantState.BlackJack)
    }

    @Test
    fun `Ready 상태에서 카드 한 장을 받고, 합이 21보다 작으면 Playing 상태로`() {
        hand.draw(HEART_ACE)
        val participantState = ParticipantState.Ready(hand)

        assertTrue(participantState.hit(HEART_SIX) is ParticipantState.Playing)
    }

    @Test
    fun `Playing 상태에서 카드 한 장을 받고, 합이 21 이하면 Playing 상태로`() {
        hand.draw(HEART_ACE)
        hand.draw(HEART_TEN)
        val participantState = ParticipantState.Playing(hand)

        assertTrue(participantState.hit(HEART_SIX) is ParticipantState.Playing)
    }

    @Test
    fun `Playing 상태에서 카드 한 장을 받고, 합이 21을 넘으면 Bust 상태로`() {
        hand.draw(HEART_JACK)
        hand.draw(HEART_TEN)
        val participantState = ParticipantState.Playing(hand)

        assertTrue(participantState.hit(HEART_SIX) is ParticipantState.Bust)
    }
}
