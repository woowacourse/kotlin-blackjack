package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어 승패 결과 테스트 - 플레이어 점수가 버스트되면 LOSE를 반환한다`() {
        val result = GameResult.resultOfPlayer(dealerSum = 18, playerSum = 22)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `플레이어 승패 결과 테스트 - 플레이어 점수가 버스트 되지 않고 딜러만 버스트되면 WIN을 반환한다`() {
        val result = GameResult.resultOfPlayer(dealerSum = 22, playerSum = 21)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어 승패 결과 테스트 - 플레이어와 딜러가 모두 버스트되지 않고 플레이어의 점수가 딜러보다 크면 WIN을 반환한다`() {
        val result = GameResult.resultOfPlayer(dealerSum = 15, playerSum = 21)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어 승패 결과 테스트 -플레이어와 딜러가 모두 버스트되지 않고 플레이어의 점수가 딜러보다 작으면 LOSE를 반환한다`() {
        val result = GameResult.resultOfPlayer(dealerSum = 21, playerSum = 15)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `플레이어 승패 결과 테스트 -플레이어와 딜러가 모두 버스트되지 않고 플레이어의 점수와 딜러 점수가 같으면 PUSH를 반환한다`() {
        val result = GameResult.resultOfPlayer(dealerSum = 21, playerSum = 21)
        assertEquals(GameResult.PUSH, result)
    }

    @Test
    fun `플레이어 승패 결과 테스트 -플레이어와 딜러가 모두 버스트되면 LOSE를 반환한다`() {
        val result = GameResult.resultOfPlayer(dealerSum = 22, playerSum = 22)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `딜러 승패 결과 테스트 - 플레이어와 딜러가 모두 버스트되면 WIN을 반환한다`() {
        val result = GameResult.resultOfDealer(dealerSum = 22, playerSum = 22)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러 승패 결과 테스트 - 플레이어가 버스트 되지 않고 딜러만 버스트되면 LOSE을 반환한다`() {
        val result = GameResult.resultOfDealer(dealerSum = 22, playerSum = 18)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `딜러 승패 결과 테스트 - 플레이어만 버스트 되면 WIN을 반환한다`() {
        val result = GameResult.resultOfDealer(dealerSum = 18, playerSum = 22)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러 승패 결과 테스트 - 딜러와 플레이어 모두 버스트되지 않고 플레이어의 점수가 딜러보다 크면 LOSE을 반환한다`() {
        val result = GameResult.resultOfDealer(dealerSum = 18, playerSum = 21)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `딜러 승패 결과 테스트 - 딜러와 플레이어 모두 버스트되지 않고 플레이어의 점수가 딜러보다 작으면 WIN을 반환한다`() {
        val result = GameResult.resultOfDealer(dealerSum = 21, playerSum = 13)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러 승패 결과 테스트 - 딜러와 플레이어 모두 버스트되지 않고 점수가 같다면 PUSH을 반환한다`() {
        val result = GameResult.resultOfDealer(dealerSum = 21, playerSum = 21)
        assertEquals(GameResult.PUSH, result)
    }
}
