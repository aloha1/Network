package yunwen.exhibition.networkroadmap.apollo

interface CharacterClient {
//    suspend fun getCountries(): List<>
    suspend fun getCharacter(): DetailCharacter?
}