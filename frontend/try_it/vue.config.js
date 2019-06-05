//模拟数据
var appData = require('./data/app.json')
const express = require('express')
const app = express()
var apiRoutes = express.Router('./data/app.json')
app.use('/api',apiRoutes)

module.exports = {
  css: {
    loaderOptions: { // 向 CSS 相关的 loader 传递选项
      less: {
        javascriptEnabled: true,
        modifyVars: {
          // '@primary-color':  '#2f3640'
        }
      }
    }
  },
  // 基本路径
  publicPath: '/',
  // 输出文件目录
  outputDir: 'dist',
  // eslint-loader 是否在保存的时候检查
  lintOnSave: true,
  // use the full build with in-browser compiler?
  // https://vuejs.org/v2/guide/installation.html#Runtime-Compiler-vs-Runtime-only
  runtimeCompiler: true,
  // webpack配置
  // see https://github.com/vuejs/vue-cli/blob/dev/docs/webpack.md
  chainWebpack: () => {},
  configureWebpack: () => {},
  // vue-loader 配置项
  // https://vue-loader.vuejs.org/en/options.html
  //vueLoader: {},
  // 生产环境是否生成 sourceMap 文件
  productionSourceMap: true,
  // use thread-loader for babel & TS in production build
  // enabled by default if the machine has more than 1 cores
  parallel: require('os').cpus().length > 1,
  // 是否启用dll
  // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#dll-mode
  // PWA 插件相关配置
  // see https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-pwa
  pwa: {},
  // webpack-dev-server 相关配置
  devServer: {
  //模拟数据开始
    open: process.platform === 'darwin',
    host: 'localhost',
    port: 8080,
    https: false,
    hotOnly: false,
    proxy: {
      '/api': {
        target: 'http://localhost:8088', //对应自己的接口
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    },
    before(app) {
      app.get('/api/get/user', (req, res) => {
        res.json({
          errno: 0,
          data: appData.User
        })
      }),
      app.get('/api/get/problems', (req, res) => {
        res.json({
          errno: 1,
          data: appData.ProblemList
        })
      }),
      app.get("/api/get/submissions", (req, res) => {
        res.json({
          error: 2,
          data: appData.SubmissionList
        })
      }),
      app.get("/api/get/ranks", (req, res) => {
        res.json({
          error: 3,
          data: appData.RankList
        })
      }),
      app.get("/api/get/rewards", (req, res) => {
        res.json({
          error: 4,
          data: appData.RewardList
        })
      }),
      app.get("/api/get/solutions", (req, res) => {
        res.json({
          error: 5,
          data: appData.SolutionList
        })
      }),
      app.get("/api/get/solution", (req, res) => {
        res.json({
          error: 6,
          data: appData.SolutionList[0]
        })
      }),
      app.get("/api/get/comments", (req, res) => {
        res.json({
          error: 7,
          data: appData.CommentList
        })
      })
    }
  },
  // 第三方插件配置
  pluginOptions: {

  }
};