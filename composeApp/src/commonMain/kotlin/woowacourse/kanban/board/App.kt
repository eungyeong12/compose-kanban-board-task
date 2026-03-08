package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import woowacourse.kanban.board.ui.home.HomeScreen
import woowacourse.kanban.board.ui.home.HomeViewModel
import woowacourse.kanban.board.ui.home.homeViewModelFactory

@Preview(showBackground = true)
@Composable
fun App() {
    val homeViewModel : HomeViewModel = viewModel(factory = homeViewModelFactory)
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiState.tasks)
}
