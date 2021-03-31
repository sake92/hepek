package docs.hepek.play

import scalatags.Text.all._
import utils.Imports.Bundle._

object Form extends HepekPlayDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Play forms")
      .withDescription("Hepek Play forms")

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  def introSection = Section(
    "Play forms",
    frag(
      s"""
      Hepek Play forms build upon basic `FormComponents`.  
      Since Play's `Field` abstraction gives us basic data needed to render a field,
        we leverage that information to render it.  
      It contains field's `name`, `id`, `label` and validation messages, so we don't have to worry about those.

      Most of the `input*` field helpers have a similar signature:
      ```scala
      def inputEmail(attrs: AttrPair*)(
          field: Field,
          label: String = DefaultLabel,
          help: String = DefaultHelp,
          validationState: Option[fc.ValidationState] = None,
          messages: Seq[String] = Seq.empty,
          transform: Frag => Frag = DefaultTransform
      )(implicit playMsgs: Messages): Frag
      ```

      Example usage:
      ```scala
      val myForm: Form[MyData] = ???
      inputEmail()(myForm("email"), help = "Please enter your email"))
      ```

      Only the `field` is mandatory, of course. 
      We already have it in our `Form` object.  
      You can and should pass a custom `label`.  
      The `help` string is optional text that goes under the field input.  

      Validation state is the state of field: success, error or warning.
      You can use it to highlight a field that needs attention.
      If form has errors, it will be set to error.  
      If you passed it explicitly, that status will be used instead.

      The `messages` are used to display a "dynamic message".  
      E.g. if you have some custom bussiness validation, not just static min/max/not-empty...

      The `transform` function is used to transform the `<input>` tag.  
      E.g. if you want to wrap it in a `<span>` and use Fontawesome or something... :)

      ---
      The `attrs` will be passed to the final `<input>` or `<textarea>`.  
      Of course, explicit `id`s, `label`s have precedence over those.
      > This parameter has its own parameter group.  
      > It's a design choice, since we can't mix varargs and default params.  
      > Although, in HTML we first write attributes and then the content, so it feels natural.
      """.md
    ),
    List(multiInputsSection)
  )

  def multiInputsSection = Section(
    "Multi-inputs",
    frag(
      s"""
      There are inputs that have multiple possible choices:
      - checkboxes, select zero or more
      - radios, select only one
      - selects, select one or more

      Each of these have its helper methods. They are similar to those described above.  
      There are some more complicated parameters, e.g. `valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])]`,
      present in `inputRadio`, `inputCheckboxes` and `inputSelect`.  
      It represents multiple inputs, so its type is read like this: `Seq[(value, label, attrs)]`.
      Not so scary, after all, right? :) 

      Ok, one more and that's it. The `valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])]`
        in the `inputSelectGrouped` helper. 
      It is read like this: `Seq[(groupLabel, Seq[(value, label, attrs)])]`.
      """.md
    )
  )
}
