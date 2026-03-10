package woowacourse.kanban.board.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import woowacourse.kanban.board.data.datasource.tasksData
import woowacourse.kanban.board.domain.entity.Task
import woowacourse.kanban.board.domain.repository.TasksRepository

class TasksRepositoryImpl: TasksRepository {
    private val tasks = MutableStateFlow(tasksData)

    override fun createTask(
        title: String,
        content: String,
        tags: List<String>,
        author: String
    ): Result<Unit> {
        val result = Task.of(title, content, tags, author)
        result.onFailure { exception ->
            return Result.failure(exception)
        }
        result.onSuccess { task ->
            tasks.update { it + task }
        }
        return Result.success(Unit)
    }

    override fun getTasks(): Result<List<Task>> = Result.success(tasks.value)
}