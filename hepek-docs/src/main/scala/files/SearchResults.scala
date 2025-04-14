package files

import ba.sake.tupson.toJson
import utils.Imports.Bundle.*
import Tags.*
import Grid.*

object SearchResults extends templates.HepekDocsStaticPage {

  override def pageContent =
    div(cls := "container")(
      div(
        h1("Search results"),
        div(id := "search-results-content")(
          p("Loading...")
        )
      )
    )

  private val fuseList = SearchIndex.fusejsIndexedPagesData.toJson

  override def scriptsInline: List[String] = super.scriptsInline ++ List(s"""
    import Fuse from 'https://cdn.jsdelivr.net/npm/fuse.js@7.1.0/dist/fuse.mjs'
    
    const urlParams = new URLSearchParams(window.location.search);
    const qParam = urlParams.get('q');
    
    const fuseIndex = await fetch('${SearchIndex.ref}').then(r => r.json())
    const myIndex = Fuse.parseIndex(fuseIndex);
    const fuse = new Fuse(${fuseList}, {
      keys: [ "title", "text" ]
    }, myIndex);
    
    const searchRes = fuse.search(qParam);
    console.log(JSON.stringify(searchRes));
    const searchResultsContentElem = document.getElementById("search-results-content");
    searchResultsContentElem.innerHTML = searchRes.map(r => {
      const page = r.item;
      return `<div class="search-result-item">
        <h2><a href="$${page.url}">$${page.title}</a></h2>
        <p>$${page.text}</p>
      </div>`;
    }).join("");
    """)
}
