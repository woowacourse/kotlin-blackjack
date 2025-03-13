package dsl.person

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String

    fun name(value: String) {
        require(value.isNotEmpty()) { NAME_EMPTY }
        name = value
    }

    fun company(value: String) {
        require(value.isNotEmpty()) { NAME_COMPANY }
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }

    companion object {
        private const val NAME_EMPTY = "빈 이름이 입력 되었습니다. 이름을 입력해 주세요."
        private const val NAME_COMPANY = "빈 회사명이 입력 되었습니다. 회사명을 입력해 주세요."
    }
}
