package woowacourse.kanban.board.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import woowacourse.kanban.board.data.TasksRepository
import woowacourse.kanban.board.data.impl.TasksRepositoryImpl
import woowacourse.kanban.board.domain.Task

data class HomeUiState(
    val tasks: List<Task> = emptyList()
)

class HomeViewModel(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        val tasks = tasksRepository.getTasks()
        _uiState.update { it.copy(tasks = tasks) }
    }
}

val homeViewModelFactory = viewModelFactory {
    initializer {
        HomeViewModel(tasksRepository = getTasksRepository())
    }
}

fun getTasksRepository(): TasksRepository = TasksRepositoryImpl()