package yunwen.exhibition.networkroadmap

data class NetData(
    val info: Info,
    val results: List<Result>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class Result(
    val name: String,
    val status: String,
    val species: String
)
