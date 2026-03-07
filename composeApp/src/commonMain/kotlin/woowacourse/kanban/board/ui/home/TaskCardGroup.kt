package woowacourse.kanban.board.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.domain.TaskCardDto

@Composable
fun TaskCardGroup(taskCardGroup: List<TaskCardDto>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(52.dp)
    ) {
        items(taskCardGroup.size) { item ->
            TaskCard(taskCard = taskCardGroup[item])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCardGroupPreview() {
    TaskCardGroup(taskCardGroup = listOf(
        TaskCardDto(
            title = "LazyColumn 컴포넌트 구현",
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = listOf("컴포넌트", "성능"),
            author = "다이노"
        ),
        TaskCardDto(
            title = "LazyColumn 컴포넌트 구현",
            content = "",
            tags = listOf("컴포넌트", "성능"),
            author = "다이노"
        ),
        TaskCardDto(
            title = "LazyColumn 컴포넌트 구현",
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = listOf(),
            author = "다이노"
        ),
        TaskCardDto(
            title = "LazyColumn 컴포넌트 구현",
            content = "",
            tags = listOf(),
            author = "다이노"
        )
    ))
}