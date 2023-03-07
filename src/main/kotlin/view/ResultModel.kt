package view

import model.FinalResult

class ResultModel {
    fun getString(result: FinalResult) = when (result) {
        FinalResult.WIN -> WIN
        FinalResult.LOSE -> LOSE
        FinalResult.PUSH -> PUSH
    }

    companion object {
        private const val WIN = "승"
        private const val LOSE = "패"
        private const val PUSH = "무"
    }
}
