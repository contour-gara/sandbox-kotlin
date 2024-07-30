package classasertion

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldNotBe

class AlphaTest : WordSpec({
    "ID1 と ID2 のクラス比較" should {
        "異なっている" {
            Alpha(1) shouldNotBe Alpha(2)
        }
    }

    "ID1 と ID1 のクラス比較" should {
        "異なっている" {
            Alpha(1) shouldNotBe Alpha(1)
        }
    }
})
