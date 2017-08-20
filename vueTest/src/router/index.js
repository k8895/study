import Router from 'vue-router'

const vgou = r => require.ensure([], () => r(require('../page/vgou/vgou')), 'vgou')
const vchild = r => require.ensure([], () => r(require('../page/vgou/vgouChild')), 'vchild')
const other = r => require.ensure([], () => r(require('../page/other/other')), 'other')
const main = r => require.ensure([], () => r(require('../page/other/main')), 'main')
const demo1 = r => require.ensure([], () => r(require('../page/demo/demo1')), 'demo1')
const demo2 = r => require.ensure([], () => r(require('../page/demo/demo2')), 'demo2')
const demo3 = r => require.ensure([], () => r(require('../page/demo/demo3')), 'demo3')
const demo4 = r => require.ensure([], () => r(require('../page/demo/demo4')), 'demo4')
const demo5 = r => require.ensure([], () => r(require('../page/demo/demo5')), 'demo5')
const demo6 = r => require.ensure([], () => r(require('../page/demo/demo6')), 'demo6')

const Bar = {template: '<div>bar</div>'}
const Baz = {template: '<div>baz</div>'}

const User = {
  template: '' +
  '<div class="user">\n' +
  '<p>user</p>' +
  '<h2>User {{ $route.params.id }}</h2>\n' +
  '<router-view></router-view>\n' +
  '</div>'
}

export default new Router({
  routes: [
    {
      path: '/',
      name: 'vgou',
      component: vgou,
      children: [{
        path: 'vchild',
        component: vchild
      }]
    },
    {
      path: '/other',
      component: other,
      children: [{
        path: '',
        components: {
          default: vgou,
          othera: Bar,
          otherb: Baz
        }
      }]
    },
    {
      path: '/user',
      component: User,
      children: [{
        path: 'other',
        component: other
      }]
    },
    {
      path: '/main',
      component: main
    },
    {
      path: '/demo1',
      component: demo1
    },
    {
      path: '/demo2',
      component: demo2
    },
    {
      path: '/demo3',
      component: demo3
    },
    {
      path: '/demo4',
      component: demo4
    },
    {
      path: '/demo5',
      component: demo5
    },
    {
      path: '/demo6',
      component: demo6
    }
  ]
})
