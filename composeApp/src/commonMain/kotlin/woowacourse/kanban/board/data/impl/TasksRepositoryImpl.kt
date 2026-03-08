package woowacourse.kanban.board.data.impl

import woowacourse.kanban.board.data.TasksRepository
import woowacourse.kanban.board.domain.Task

class TasksRepositoryImpl: TasksRepository {
    override fun getTasks(): List<Task> {
        val tasks = tasksData
        return tasks
    }
}