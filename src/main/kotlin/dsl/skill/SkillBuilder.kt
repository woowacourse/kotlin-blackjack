package dsl.skill

class SkillBuilder {
    private lateinit var soft: String
    private lateinit var hard: String
    
    fun soft(value: String){
        soft = value
    }

    fun hard(value: String){
        hard = value
    }
    
    fun build(): Skills {
        return Skills(soft, hard)
    }
}