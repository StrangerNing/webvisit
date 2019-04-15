import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/authRedirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', noCache: true, affix: true }
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/index',
    alwaysShow: true, // will always show the root menu
    meta: {
      title: 'permission',
      icon: 'lock',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'page',
        component: () => import('@/views/permission/page'),
        name: 'PagePermission',
        meta: {
          title: 'pagePermission',
          roles: ['admin'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'directive',
        component: () => import('@/views/permission/directive'),
        name: 'DirectivePermission',
        meta: {
          title: 'directivePermission'
          // if do not set roles, means: this page does not require permission
        }
      }
    ]
  },
  {
    path: '/attence',
    component: Layout,
    redirect: '/attence/index',
    alwaysShow: true,
    meta: {
      title: 'attence',
      icon: 'peoples',
      roles: ['admin', 'editor']
    },
    children: [
      {
        path: 'edit',
        component: () => import('@/views/attence/edit'),
        name: 'AttenceEdit',
        meta: {
          title: 'attenceEdit',
          roles: ['admin'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'report',
        component: () => import('@/views/attence/attenceReport'),
        name: 'AttenceReport',
        meta: {
          title: 'attenceReport',
          roles: ['admin']
          // if do not set roles, means: this page does not require permission
        }
      },
      {
        path: 'detail',
        component: () => import('@/views/attence/holidayDetail'),
        name: 'HolidayDetail',
        meta: {
          title: 'holidayDetail',
          roles: ['admin', 'editor']
        }
      }
    ]
  },
  {
    path: '/yellowPages',
    component: Layout,
    redirect: '/yellowPages/index',
    alwaysShow: true,
    meta: {
      title: 'yellowPages',
      icon: 'excel',
      roles: ['admin', 'editor']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/yellow-page/index'),
        name: 'YellowPageEdit',
        meta: {
          title: 'yellowPageEdit',
          roles: ['admin'] // or you can only set roles in sub nav
        }
      }
    ]
  },
  {
    path: '/security',
    component: Layout,
    redirect: '/security/log',
    alwaysShow: true,
    meta: {
      title: 'security',
      icon: 'lock',
      roles: ['admin']
    },
    children: [
      {
        path: 'log',
        component: () => import('@/views/security/log'),
        name: 'log',
        meta: {
          title: 'log',
          roles: ['admin'] // or you can only set roles in sub nav
        }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
