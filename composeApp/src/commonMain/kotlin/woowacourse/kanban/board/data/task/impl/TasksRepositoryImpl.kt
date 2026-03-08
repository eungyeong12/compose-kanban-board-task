package woowacourse.kanban.board.data.task.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import woowacourse.kanban.board.data.Result
import woowacourse.kanban.board.data.task.TasksRepository
import woowacourse.kanban.board.domain.Task

class TasksRepositoryImpl: TasksRepository {
    private val tasks = MutableStateFlow(tasksData)

    override fun createTask(
        title: String,
        content: String,
        tags: List<String>,
        author: String
    ): Result<Unit> {
        if (title.isBlank()) return Result.Error(Exception("제목을 입력해주세요"))
        if (author.isBlank()) return Result.Error(Exception("작성자를 입력해주세요"))
        if (tags.size > 5) return Result.Error(Exception("태그는 최대 5개까지 입력할 수 있습니다"))
        if (tags.isNotEmpty()) {
            tags.forEach { if (it.length > 5) return Result.Error(Exception("태그는 최대 5자까지 입력할 수 있습니다")) }
        }
        tasks.update { it + Task(title, content, tags, author) }
        return Result.Success(Unit)
    }

    override fun getTasks(): Result<List<Task>> = Result.Success(tasks.value)
}