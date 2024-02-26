package classasertion

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class BetaTest {
  @Test
  fun ID1とID2のクラスの場合失敗する() {
    // execute
    val actual = Beta(1)

    // expected
    val expected = Beta(2)
    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun ID1とID1のクラスの場合成功する() {
    // execute
    val actual = Beta(1)

    // expected
    val expected = Beta(1)
    assertThat(actual).isEqualTo(expected)
  }
}
