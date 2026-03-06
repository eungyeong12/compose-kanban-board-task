package woowacourse.kanban.board.taskcard

data class TaskCardDto(
    val title: String,
    val content: String,
    val tags: List<String>,
    val author: String,
)