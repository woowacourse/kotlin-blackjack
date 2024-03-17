package blackjack.view

import blackjack.model.DrawDecision
import blackjack.model.ParticipantName

object ProgressInputView {
    private const val INPUT_MESSAGE_DRAW_DECISION = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val NO_DRAW_DECISION_INPUT = ""

    fun inputDrawDecision(name: ParticipantName): DrawDecision {
        println(INPUT_MESSAGE_DRAW_DECISION.format(name))
        return try {
            val drawDecision = readlnOrNull() ?: NO_DRAW_DECISION_INPUT
            DrawDecision(drawDecision)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            inputDrawDecision(name)
        }
    }
}
