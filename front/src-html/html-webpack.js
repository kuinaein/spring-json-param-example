'use strict';

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const config = require('../config');

const pages = require('./pages');


const COMMON_CHUNKS = ['manifest', 'vendor'];

function generateHtmlWebpackSettings() {
  const baseConfig = configure();
  return pages.map(page => {
    const relPath = page.path.startsWith('/') ?
        page.path.substring(1) : page.path;
    const roots = resolveRelativeRoots(relPath);
    const htmlWebpackConfig = Object.assign({
      filename: relPath +
          (page.path.endsWith('/') ? 'index.html' : '.html'),
    }, baseConfig);
    htmlWebpackConfig.ejsVars = Object.assign(roots,
        baseConfig.ejsVars, page);
    Array.prototype.push.apply(htmlWebpackConfig.ejsVars.chunks,
        COMMON_CHUNKS);
    return new HtmlWebpackPlugin(htmlWebpackConfig);
  });
}

function configure() {
  // (moved from /build/webpack.prod.conf.js)
  // generate dist index.html with correct asset hash for caching.
  // you can customize output by editing /index.html
  // see https://github.com/ampedandwired/html-webpack-plugin
  const baseConfig = {
    template: path.join(__dirname, 'template.ejs'),
    inject: false,
  };
  if ('production' === process.env.NODE_ENV) {
    Object.assign(baseConfig, {
      minify: {
        removeComments: true,
        collapseWhitespace: true,
        // Thymeleaf requires "valid" XHTML
        // removeAttributeQuotes: true,
        keepClosingSlash: true,

        // more options:
        // https://github.com/kangax/html-minifier#options-quick-reference
      },
      // necessary to consistently work with multiple chunks via CommonsChunkPlugin
      chunksSortMode: 'dependency',
    })
  }
  return baseConfig;
}

function resolveRelativeRoots(pagePath) {
  let relativeStaticRoot;
  let relativeCompiledRoot;
  // /templates/... => /templates/
  const depth = pagePath.replace(/[^/]/g, '').length;
  const relativeTemplateRoot =
      new Array(depth).fill('..').join('/');
  if ('production' === process.env.NODE_ENV) {
    // /templates/ => /static
    if ('' === relativeTemplateRoot) {
      relativeStaticRoot = '../static';;
      relativeCompiledRoot = '';
    } else {
      relativeStaticRoot = relativeTemplateRoot + '/../static';
      relativeCompiledRoot = relativeTemplateRoot + '/';
    }
  } else { // development env.
    relativeStaticRoot =
        config.dev.assetsPublicPath + config.dev.assetsSubDirectory;
    relativeCompiledRoot = '';
  }
  return {
    relativeStaticRoot,
    relativeCompiledRoot,
    dummyContextPath: relativeTemplateRoot,
  }
}

module.exports = generateHtmlWebpackSettings();
