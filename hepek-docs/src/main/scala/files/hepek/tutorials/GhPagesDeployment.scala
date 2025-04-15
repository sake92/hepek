package files.hepek.tutorials

import scalatags.Text.all.*
import utils.Imports.*
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
      ```yaml
        name: Deploy GhPages
        on:
          push:
            branches:
              - main
        permissions:
          contents: write
        jobs:
          build-and-deploy:
            runs-on: ubuntu-latest
            steps:
              - uses: actions/checkout@v4
              - uses: actions/setup-java@v3
                with:
                  distribution: temurin
                  java-version: 11

              - name: Build
                run: ./mill site.hepek
                # or sbt hepek

              - name: Deploy
                uses: JamesIves/github-pages-deploy-action@v4
                with:
                  folder: site/hepek_output
    """.md
    )
  )
}
