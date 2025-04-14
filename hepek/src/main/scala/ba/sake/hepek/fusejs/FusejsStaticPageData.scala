package ba.sake.hepek.fusejs

import ba.sake.tupson.JsonRW

case class FusejsStaticPageData(
    title: String,
    text: String,
    url: String
) derives JsonRW
