package files.hepek.tutorials

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object GhPagesDeployment extends HepekTutorialPage {

  override def pageSettings =
    super.pageSettings.withTitle("GitHub Pages Deployment")

  override def blogSettings = super.blogSettings
    .withSections(deployGhPagesSection)

  val deployGhPagesSection = Section(
    "Deploy to GH Pages",
    frag(
      raw"""
      Add `.github\workflows\ghpages.yml` to your repo:
    """.md,
      chl.yaml("""
        name: Build and Deploy GhPages docs

        on:
          push:
            branches:
              - main

        jobs:
          build-and-deploy:
            runs-on: ubuntu-latest
            steps:
              - uses: actions/checkout@v4
              - uses: actions/setup-java@v3
                with:
                  distribution: temurin
                  java-version: 11
                  cache: sbt          

              - name: Build
                run: sbt hepek
                # or ./mill site.hepek

              - name: Deploy
                uses: JamesIves/github-pages-deploy-action@v4
                with:
                  folder: site/hepek_output
    """)
    )
  )
}
