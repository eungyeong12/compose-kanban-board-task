package woowacourse.kanban.board.taskcard

data class TaskCard(
    val title: String,
    val content: String,
    val tags: List<String>,
    val author: String,
)
