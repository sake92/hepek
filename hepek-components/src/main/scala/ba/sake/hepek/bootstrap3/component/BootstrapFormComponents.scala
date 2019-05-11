package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all
import all.{form => _, _}
import ba.sake.hepek.html.component.FormComponents
import scalatags.Text

object BootstrapFormComponents extends BootstrapFormComponents {
  sealed trait Type extends FormComponents.Type

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

  override def formType: FormComponents.Type = Type.Vertical

  override def inputWithType(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputAttrs: Seq[AttrPair]
  ) = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    formType match {
      case Type.Horizontal(labelRatio, inputRatio) =>
        val labelRatioBootstrap =
          ((labelRatio / (labelRatio + inputRatio).toDouble) * 12).toInt
        val inputRatioBootstrap =
          ((inputRatio / (labelRatio + inputRatio).toDouble) * 12).toInt
        bsHorizontalFormGroup(
          inputType,
          inputName,
          inputLabel,
          inputId,
          inputValue,
          labelRatioBootstrap,
          inputRatioBootstrap,
          inputAttrs: _*
        )
      case _ =>
        if (inputType == "checkbox") {
          div(cls := "checkbox")(
            label(inputId.map(`for` := _))(
              input(commonAttrs),
              inputLabel
            )
          )
        } else if (isButtonLike(inputType)) {
          input(
            cls := "btn ",
            commonAttrs
          )
        } else {
          inputLabel match {
            case None =>
              div(cls := "form-group")(
                input(cls := "form-control", commonAttrs)
              )
            case Some(inputLabel) =>
              div(cls := "form-group")(
                label(inputId.map(`for` := _))(inputLabel),
                input(cls := "form-control", commonAttrs)
              )
          }

        }
    }
  }

  private def bsHorizontalFormGroup(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      labelRatioBootstrap: Int,
      inputRatioBootstrap: Int,
      inputAttrs: AttrPair*
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    if (inputType == "checkbox") {
      div(cls := "form-group")(
        div(
          cls := s"col-sm-offset-$labelRatioBootstrap col-sm-$inputRatioBootstrap"
        )(
          div(cls := "checkbox")(
            label(inputId.map(`for` := _))(
              input(commonAttrs),
              inputLabel
            )
          )
        )
      )
    } else if (isButtonLike(inputType)) {
      div(cls := "form-group")(
        div(
          cls := s"col-sm-offset-$labelRatioBootstrap col-sm-$inputRatioBootstrap"
        )(
          input(
            cls := "btn ",
            commonAttrs
          )
        )
      )
    } else {
      div(cls := "form-group")(
        label(cls := s"control-label col-sm-$labelRatioBootstrap", inputId.map(`for` := _))(
          inputLabel
        ),
        div(cls := s"col-sm-$inputRatioBootstrap")(
          input(cls := "form-control", commonAttrs)
        )
      )
    }
  }
}
