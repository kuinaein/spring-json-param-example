'use strict';

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const config = require('../config');

const outputFiles = [
  'index',
  'a/index',
  'a/create',
];

function generateHtmlWebpackSettings() {
  const baseConfig = configure();
  return outputFiles.map(f => {
    let relativeStaticRoot;
    let relativeCompiledRoot;
    if ('production' === process.env.NODE_ENV) {
      // /templates/compiled/... => /static
      const depth = f.replace(/[^/]/g, '').length;
      const relativeTemplateRoot =
          new Array(depth).fill('..').join('/');
      if ('' === relativeTemplateRoot) {
        relativeStaticRoot = '../../static';;
        relativeCompiledRoot = '';
      } else {
        relativeStaticRoot = relativeTemplateRoot + '/../../static';
        relativeCompiledRoot = relativeTemplateRoot + '/';
      }
    } else {
      relativeStaticRoot =
          config.dev.assetsPublicPath + config.dev.assetsSubDirectory;
      relativeCompiledRoot = '';
    }

    const htmlWebpackConfig = Object.assign({
      filename: f + '.html',
      ejsVars: {
        relativeStaticRoot,
        relativeCompiledRoot,
      },
    }, baseConfig);
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
        removeAttributeQuotes: true
        // more options:
        // https://github.com/kangax/html-minifier#options-quick-reference
      },
      // necessary to consistently work with multiple chunks via CommonsChunkPlugin
      chunksSortMode: 'dependency',
    })
  }
  return baseConfig;
}

module.exports = generateHtmlWebpackSettings();
