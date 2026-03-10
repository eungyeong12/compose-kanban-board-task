package woowacourse.kanban.board.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TagTest {

    @Test
    fun `태그 생성 성공 테스트`() {
        // given
        val name = "tag"

        // when
        val result = runCatching { Tag(name) }

        // then
        assertThat(result.isSuccess).isTrue
        assertThat(result.getOrNull()).isNotNull
        assertThat(result.getOrNull()?.name).isEqualTo("tag")
    }

    @Test
    fun `태스크 내용이 비어 있는 경우 생성이 불가능하다`() {
        // given
        val name = ""

        // when
        val result = runCatching { Tag(name) }

        // then
        assertThat(result.isFailure).isTrue
        assertThat(result.exceptionOrNull()).isNotNull
        assertThat(result.exceptionOrNull()?.message).isEqualTo("태그 내용이 비어 있습니다")
    }

    @Test
    fun `태그의 길이가 5자를 초과할 경우 생성이 불가능하다`() {
        // given
        val name = "abcedf"

        // when
        val result = runCatching { Tag(name) }

        // then
        assertThat(result.isFailure).isTrue
        assertThat(result.exceptionOrNull()).isNotNull
        assertThat(result.exceptionOrNull()?.message).isEqualTo("태그의 길이는 5자 이하여야 합니다")
    }
}