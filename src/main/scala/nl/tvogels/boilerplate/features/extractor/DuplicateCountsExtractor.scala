package nl.tvogels.boilerplate.features.extractor

import nl.tvogels.boilerplate.cdom.{Node,CDOM}
import nl.tvogels.boilerplate.features.BlockFeatureExtractor
import nl.tvogels.boilerplate.utilities.Util
import scala.math.log

object DuplicateCountsExtractor extends BlockFeatureExtractor {

  implicit def b2d(b: Boolean): Double =
    if (b) 1.0 else -1.0

  def z (v: Double, mean: Double, sd: Double): Double =
    (v - mean) / sd

  def apply(cdom: CDOM) = (node: Node) => {
    val textC = cdom.textCounts(node.text)
    val classC = cdom.classSelectorCounts(node.classSelector)
    Vector(
      textC > 1,
      textC > 10,
      cdom.classSelectorCounts(node.classSelector) / cdom.leaves.length
    )
  }

  val labels = Vector(
    "has_duplicate",
    "has_10_duplicates",
    "n_same_class_path"
  )

}