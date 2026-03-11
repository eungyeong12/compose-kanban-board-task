package woowacourse.kanban.board.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TaskTest {

    @Test
    fun `제목, 내용, 태그, 작성자 값이 유효할 경우 태스크를 생성한다`() {
        // given
        val title = "title"
        val content = "content"
        val tags = listOf("tag1", "tag2")
        val author = "author"

        // when
        val result = runCatching { Task.of(title, content, tags, author) }

        // then
        assertThat(result.isSuccess).isTrue
        assertThat(result.getOrNull()).isNotNull
        assertThat(result.getOrNull()?.title).isEqualTo("title")
        assertThat(result.getOrNull()?.content).isEqualTo("content")
        assertThat(result.getOrNull()?.tags).isEqualTo(listOf(Tag("tag1"), Tag("tag2")))
        assertThat(result.getOrNull()?.author).isEqualTo("author")
    }

    @Test
    fun `제목이 비어 있거나 공백만 있는 경우 생성이 불가능하다`() {
        // given
        val title = ""

        // when
        val result = runCatching { Task.of(title, "content", listOf("tag1", "tag2"), "author") }

        // then
        assertThat(result.isFailure).isTrue
        assertThat(result.exceptionOrNull()).isNotNull
        assertThat(result.exceptionOrNull()?.message).isEqualTo("제목을 입력해주세요")
    }

    @Test
    fun `담당자가 비어 있거나 공백만 있는 경우 생성이 불가능하다`() {
        // given
        val author = ""

        // when
        val result = runCatching { Task.of("title", "content", listOf("tag1", "tag2"), author) }

        // then
        assertThat(result.isFailure).isTrue
        assertThat(result.exceptionOrNull()).isNotNull
        assertThat(result.exceptionOrNull()?.message).isEqualTo("작성자를 입력해주세요")
    }

    @Test
    fun `태그가 5개 초과인 경우 생성이 불가능하다`() {
        // given
        val tags = listOf("tag1", "tag2", "tag3", "tag4", "tag5", "tag6")

        // when
        val result = runCatching { Task.of("title", "content", tags, "author") }

        // then
        assertThat(result.isFailure).isTrue
        assertThat(result.exceptionOrNull()).isNotNull
        assertThat(result.exceptionOrNull()?.message).isEqualTo("태그는 최대 5개까지 입력할 수 있습니다")
    }
}