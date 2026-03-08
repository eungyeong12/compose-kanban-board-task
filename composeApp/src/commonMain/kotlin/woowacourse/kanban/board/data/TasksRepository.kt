package woowacourse.kanban.board.data

import woowacourse.kanban.board.domain.Task

interface TasksRepository {
    fun getTasks(): List<Task>
}