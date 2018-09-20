package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all
import all.{form => _, _}
import ba.sake.hepek.html.component.FormComponents

object BootstrapFormComponents extends BootstrapFormComponents {
  sealed trait Type { def classes: String = "" }

  object Type {
    case object Vertical extends Type
    case object Inline   extends Type { override def classes = "form-inline" }
    case class Horizontal(labelRatio: Int = 1, inputRatio: Int = 3) extends Type {
      require(labelRatio > 0, "Label ratio < 1")
      require(inputRatio > 0, "Input ratio < 1")
      override def classes = "form-horizontal"
    }
  }
}

trait BootstrapFormComponents extends FormComponents {
  import BootstrapFormComponents._

  def bootstrapFormType: Type = Type.Vertical

  override def form(_formAttrs: AttrPair*)(content: Frag*): Frag = {
    val newFormAttrs = _formAttrs :+ (cls := bootstrapFormType.classes)
    all.form(newFormAttrs)(content)
  }

  override def inputWithType(
      _type: String,
      _label: String,
      _inputAttrs: AttrPair*
  ) = {
    val inputId            = getAttrValue(_inputAttrs, "id")
    val inputAttrsFiltered = _inputAttrs.filterNot(_.a.name == "type") // ignore type

    bootstrapFormType match {
      case Type.Horizontal(labelRatio, inputRatio) =>
        val labelRatioBootstrap =
          ((labelRatio / (labelRatio + inputRatio).toDouble) * 12).toInt
        val inputRatioBootstrap =
          ((inputRatio / (labelRatio + inputRatio).toDouble) * 12).toInt
        bsHorizontalFormGroup(
          _type,
          _label,
          inputId,
          labelRatioBootstrap,
          inputRatioBootstrap,
          inputAttrsFiltered,
          _inputAttrs: _*
        )
      case _ =>
        if (_type == "checkbox") {
          div(cls := "checkbox")(
            label(inputId.map(`for` := _))(
              input(tpe := _type, inputAttrsFiltered),
              _label
            )
          )
        } else if (isButtonLike(_type)) {
          val inputAttrsFiltered2 =
            inputAttrsFiltered.filterNot(_.a.name == "value") // ignore value
          input(tpe := _type, value := _label, cls := "btn ", inputAttrsFiltered2)
        } else {
          div(cls := "form-group")(
            label(inputId.map(`for` := _))(_label),
            input(tpe := _type, cls := "form-control", inputAttrsFiltered)
          )
        }
    }
  }

  private def bsHorizontalFormGroup(
      _type: String,
      _label: String,
      inputId: Option[String],
      labelRatioBootstrap: Int,
      inputRatioBootstrap: Int,
      inputAttrsFiltered: Seq[AttrPair],
      _inputAttrs: AttrPair*
  ): Frag =
    if (_type == "checkbox") {
      div(cls := "form-group")(
        div(
          cls := s"col-sm-offset-$labelRatioBootstrap col-sm-$inputRatioBootstrap"
        )(
          div(cls := "checkbox")(
            label(inputId.map(`for` := _))(
              input(tpe := _type, inputAttrsFiltered),
              _label
            )
          )
        )
      )
    } else if (isButtonLike(_type)) {
      val inputAttrsFiltered2 =
        inputAttrsFiltered.filterNot(_.a.name == "value") // ignore value

      div(cls := "form-group")(
        div(
          cls := s"col-sm-offset-$labelRatioBootstrap col-sm-$inputRatioBootstrap"
        )(
          input(tpe := _type, value := _label, cls := "btn ", inputAttrsFiltered2)
        )
      )
    } else {
      div(cls := "form-group")(
        label(inputId.map(`for` := _), cls := s"control-label col-sm-$labelRatioBootstrap")(_label),
        div(cls := s"col-sm-$inputRatioBootstrap")(
          input(tpe := _type, cls := "form-control", inputAttrsFiltered)
        )
      )
    }
}
