package blackjack.domain.model

class Record(private val _verdictResults: List<VerdictResult> = emptyList()) {
    val verdictResults get() = _verdictResults.toList()

    fun progress(vararg verdictResult: VerdictResult) = Record(verdictResults + verdictResult)

    fun lastVerdictResult() = verdictResults.last()
}
