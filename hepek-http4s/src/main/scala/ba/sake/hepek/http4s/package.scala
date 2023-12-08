package ba.sake.hepek.http4s

import ba.sake.hepek.html.HtmlPage
import org.http4s.*
import org.http4s.Charset.`UTF-8`
import org.http4s.headers.`Content-Type`

given htmlPageEncoder[F[_], C <: HtmlPage](using
    charset: Charset = `UTF-8`
): EntityEncoder[F, C] =
  EntityEncoder
    .stringEncoder[F]
    .contramap[C](content => content.contents.render)
    .withContentType(`Content-Type`(MediaType.text.html, charset))
