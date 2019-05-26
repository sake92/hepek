# Changelog
All notable changes to this project will be documented in this file.


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