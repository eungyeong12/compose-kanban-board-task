package woowacourse.kanban.board.data.task

import woowacourse.kanban.board.data.Result
import woowacourse.kanban.board.domain.Task

interface TasksRepository {
    fun createTask(
        title: String,
        content: String,
        tags: List<String>,
        author: String
    ): Result<Unit>
    fun getTasks(): Result<List<Task>>
}