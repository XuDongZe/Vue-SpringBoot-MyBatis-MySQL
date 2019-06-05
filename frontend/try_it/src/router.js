import Vue from 'vue'
import Router from 'vue-router'

// content-right
import MyProblemsCardPanel from '@/components/Problem/MyProblemsCardPanel'
import HotProblemsPanel from '@/components/Problem/HotProblemsPanel'
import HotArticlesPanel from '@/components/Article/HotArticlesPanel'

// center-modal
import LoginModal from '@/components/Login/LoginModal'

//编辑
//  edit
//    solution
//    problem
//    article
import SolutionEditPanel from '@/components/Problem/SolutionEditPanel' //文章详情
import ProblemEditingPanel from '@/components/Problem/ProblemEditingPanel' //题目上传
import ArticleEditingPanel from '@/components/Article/ArticleEditingPanel' //写文章

//题库
//  problems
//    lists
//    about
//      details
//      submissions
//      discussions
//      solutions
import ProblemsPanel from '@/components/Problem/ProblemsPanel'
import ProblemAboutPanel from '@/components/Problem/ProblemAboutPanel'
import ProblemDetailsPanel from '@/components/Problem/ProblemDetailsPanel'
import ProblemSubmissonPanel from '@/components/Problem/ProblemSubmissonPanel'
import ProblemDiscussionPanel from '@/components/Problem/ProblemDiscussionPanel'
import ProblemSolutionPanel from '@/components/Problem/ProblemSolutionPanel'

//文章库
//  articles
//    lists
//    about
//      details
//      discussions 
import ArticlesPanel from '@/components/Article/ArticlesPanel'
import ArticleAboutPanel from '@/components/Article/ArticleAboutPanel'
import ArticleDetailsPanel from '@/components/Article/ArticleDetailsPanel'
import ArticleDiscussionPanel from '@/components/Article/ArticleDiscussionPanel'

import ActivitiesPanel from '@/components/ActivitiesPanel' // 活动
import RanksPanel from '@/components/RanksPanel' // 排行榜

//  personal
//    info( edit + preview )
import PersonalInfoPanel from '@/components/Personal/PersonalInfoPanel' // 个人统计信息

//  personal
//    upload
//      problems -> { problems/about?id=; }
//      articles -> { articles/about?id=; }
import PersonalUploadPanel from '@/components/Personal/PersonalUploadPanel' // 个人上传
import PersonalProblemsPanel from "@/components/Personal/PersonalProblemsPanel"
import PersonalArticlesPanel from "@/components/Personal/PersonalArticlesPanel"

//  personal
//    favorite
//      problems -> { problems/about?id=; }
//      articles -> { articles/about?id=; }
import PersonalFavoritePanel from '@/components/Personal/PersonalFavoritePanel' //收藏夹
import FavoriteProblemsPanel from "@/components/Personal/FavoriteProblemsPanel"
import FavoriteArticlesPanel from "@/components/Personal/FavoriteArticlesPanel"

//  personal
//    history
//      errors -> { problems/about } 
//      activities
import PersonalHistoryPanel from '@/components/Personal/PersonalHistoryPanel'
import PersonalWrongsPanel from '@/components/Personal/PersonalWrongsPanel' //历史 -> 错题
import PersonalActivitiesPanel from '@/components/Personal/PersonalActivitiesPanel'

Vue.use(Router)

export default new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
  // edit
    {
      path: '/edit',
      component: {
        template: `
          <router-view></router-view>
        `
      },
      children: [
        {
          path: 'solution',
          component: SolutionEditPanel,
          rewrite: ""
        },
        {
          path: 'problem',
          component: ProblemEditingPanel
        },
        {
          path: 'article',
          component: ArticleEditingPanel
        }
      ]
    },
// headbar -> home
    {
      path: '/',
      components: {
        MyProblemsPanel: MyProblemsCardPanel,
        HotProblemsPanel: HotProblemsPanel,
        HotArticlesPanel: HotArticlesPanel,
      }
    },
    {
      path: '/home',
      redirect: '/'
    },
// headbar -> problems
    {
      path: '/problems',
      component: {
        template: `
          <router-view></router-view>
        `
      },
      redirect: '/problems/lists',
      children: [
        {
          path: 'lists',
          component: ProblemsPanel
        },
        {
          path: 'about',
          component: ProblemAboutPanel,
          redirect: "/problems/about/details",
          children: [
            {
              path: 'details',
              component: ProblemDetailsPanel
            },
            {
              path: 'submissions',
              component: ProblemSubmissonPanel
            },
            {
              path: 'discussions',
              component: ProblemDiscussionPanel
            },
            {
              path: 'sulotions',
              component: ProblemSolutionPanel
            }
          ]
        },
      ]
    },
//  home -> articles
    {
      path: '/articles',
      component: {
        template: `
          <router-view></router-view>
        `
      },
      redirect: '/articles/lists',
      children: [
        {
          path: "lists",
          component: ArticlesPanel
        },
        {
          path: "about",
          component: ArticleAboutPanel,
          redirect: "/articles/about/details",
          children: [
            {
              path: 'details',
              component: ArticleDetailsPanel
            },
            {
              path: 'discussions',
              component: ArticleDiscussionPanel
            }
          ]
        }
      ],
    },

    {
      path: '/activities',
      component: ActivitiesPanel
    },
    {
      path: '/ranks',
      component: RanksPanel
    },
  // side bar
    {
      path: '/personal',
      component: {
        template: `
          <router-view></router-view>
        `
      },
      redirect: '/personal/info',
      children: [
        {
          path: 'info',
          component: PersonalInfoPanel
        },
        {
          path: 'upload',
          component: PersonalUploadPanel,
          redirect: '/personal/upload/problems',
          children: [
            {
              path: 'problems',
              component: PersonalProblemsPanel
            },
            {
              path: 'articles',
              component: PersonalArticlesPanel
            }
          ]
        },
        {
          path: 'favorite',
          component: PersonalFavoritePanel,
          redirect: '/personal/favorite/problems',
          children: [
            {
              path: 'problems',
              component: FavoriteProblemsPanel
            },
            {
              path: 'articles',
              component: FavoriteArticlesPanel
            }
          ]
        },
        {
          path: 'history', //历史
          component: PersonalHistoryPanel,
          redirect: '/personal/history/errors',
          children: [
            {
              path: 'errors',
              component: PersonalWrongsPanel
            },
            {
              path: 'activities',
              component: PersonalActivitiesPanel
            }
          ]
        }
      ]
    }
  ]
})
