# Changelog
All notable changes to this project will be documented in this file.

## 0.30.0
- add scala-cli support

## 0.27.0
- add AlpineJS support

## 0.25.0
- add HTMX support

## 0.24.0
- re-add Play 2 support

## 0.21.0
- add ZIO support

## 0.20.0
- add Play 3 support
- add http4s support

## 0.15.0
- add bootstrap 5 support

## 0.14.0
- remove w3css support

## 0.13.0
- move from Java 8 to Java 11
- remove plantuml support

## 0.10.0
- migrate to Scala 3
- remove Play 2 support

## 0.8.3
Proper ScalaJS support. Finally!

## 0.6.0
- MultiRenderable support

## 0.4.0
- Play framework 2 support

## 0.3.1
- `half1`, `half2` are now `half`. Same for `third1` etc.
- introduced `Bundle` trait, a collection of same-framework components
- added Bulma support, thanks to [@P3trur0](https://github.com/P3trur0)

## 0.2.0
- refactored `page*` and `site*` flat properties to more structured `PageSettings` and `SiteSettings` (now use `with*` on those objects, autocomplete for free and more readable)
- refactored dependencies structure, added `components`
- added `implicits` for common imports

## 0.1.2
- added PureCSS support ([#16](https://github.com/sake92/hepek/pull/16))
- added form support

## 0.1.1 (first release)
- basic Bootstrap3 support: dependencies, grid, navbar
- Markdown support via Commonmark
- Prismjs support
- Katex and Mathjax support
- PDF render support
