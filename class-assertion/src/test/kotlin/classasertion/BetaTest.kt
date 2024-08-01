package classasertion

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BetaTest : WordSpec({
    "ID1 と ID2 のクラス比較" should {
        "異なっている" {
            Beta(1) shouldNotBe Beta(2)
        }
    }

    "ID1 と ID1 のクラス比較" should {
        "同じである" {
            Beta(1) shouldBe Beta(1)
        }
    }
})
