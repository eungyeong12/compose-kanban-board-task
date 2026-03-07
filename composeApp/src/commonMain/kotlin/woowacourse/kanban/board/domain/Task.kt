package woowacourse.kanban.board.domain

data class Task(
    val title: String,
    val content: String,
    val tags: List<String>,
    val author: String,
)