# Hepek [![Maven Central](https://img.shields.io/maven-central/v/ba.sake/hepek_2.12.svg?style=flat-square&label=Scala+2.12)](https://mvnrepository.com/artifact/ba.sake/hepek) [![Build Status](	https://img.shields.io/travis/sake92/hepek/master.svg?logo=travis&style=flat-square)](https://travis-ci.org/sake92/hepek) [![Gitter](https://img.shields.io/gitter/room/sake92/hepek.svg?style=flat-square)](https://gitter.im/sake92/hepek?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Docs and examples
- [docs](https://sake92.github.io/hepek)
- [examples](https://github.com/sake92/hepek-examples)
- [source code](https://github.com/sake92/sake-ba-source) of sake.ba

Start with cloning the [starter template](https://github.com/sake92/hepek-starter)  
or deploy+fork it with Netlify (yes, for free) in just a few clicks:  

[![Deploy to Netlify](https://www.netlify.com/img/deploy/button.svg)](https://app.netlify.com/start/deploy?repository=https://github.com/sake92/hepek-starter)

## Features
- full Scala support, statically typed templates etc.
- **automatic relative paths**, just type `MyPage.ref`!!!
- **incremental** rendering by default
- **typesafe grids** (Bootstrap3, PureCSS, Bulma), never worry to miss a `col-*`
- Markdown support (Commonmark)
- code highlighting (Prismjs)
- math support (KaTeX, Mathjax)
- form helpers
- file watching with sbt
- PDF rendering

## TODO
- dynamic pages, e.g. replace Play's Twirl templates :)
- ScalaJS
- integrate more CSS and JS libraries/frameworks

## Changelog

### 0.3.1
- `half1`, `half2` are now `half`. Same for `third1` etc.
- introduced `Bundle` trait, a collection of same-framework components
- added Bulma support, thanks to [@P3trur0](https://github.com/P3trur0)

### 0.2.0
- refactored `page*` and `site*` flat properties to more structured `PageSettings` and `SiteSettings` (now use `with*` on those objects, autocomplete for free and more readable)
- refactored dependencies structure, added `components`
- added `implicits` for common imports

### 0.1.2
- added PureCSS support ([#16](https://github.com/sake92/hepek/pull/16))
- added form support

### 0.1.1 (first release)
- basic Bootstrap3 support: dependencies, grid, navbar
- Markdown support via Commonmark
- Prismjs support
- Katex and Mathjax support
- PDF render support
