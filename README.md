# Hepek [![Build Status](https://travis-ci.org/sake92/hepek.svg?branch=master)](https://travis-ci.org/sake92/hepek) [![Join the chat at https://gitter.im/sake92/hepek](https://badges.gitter.im/sake92/hepek.svg)](https://gitter.im/sake92/hepek?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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
- Markdown support
- Bootstrap3, PureCSS helpers (with **typesafe grids**!)
- Prismjs code highlighter
- KaTeX or Mathjax for math formulas
- form helpers
- file watching with sbt
- PDF rendering

## TODO
- dynamic pages support, e.g. replace Play's Twirl templates :)
- integrate more CSS and JS libraries/frameworks

## Changelog

### 0.2.0
- refactored `page*` and `site*` flat propertes to more structured `PageSettings` and `SiteSettings` (now use `with*` on those objects, autocomplete for free and more readable IMHO)
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
