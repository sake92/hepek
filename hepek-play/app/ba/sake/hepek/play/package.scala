package ba.sake.hepek.play

import ba.sake.hepek.html.HtmlPage
import play.api.http.ContentTypeOf
import play.api.http.ContentTypes
import play.api.http.Writeable
import play.api.mvc.Codec

given ContentTypeOf[HtmlPage] =
  ContentTypeOf(Some(ContentTypes.HTML))

given htmlPage2Writeable(using codec: Codec): Writeable[HtmlPage] =
  Writeable(page => codec.encode(page.contents.render))
