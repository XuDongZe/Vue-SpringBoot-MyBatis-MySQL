import Vue from 'vue'
import { Layout } from 'ant-design-vue'
import { Menu } from 'ant-design-vue'
import { Breadcrumb } from 'ant-design-vue'
import { Row } from 'ant-design-vue'
import { Col } from 'ant-design-vue'
import { Icon } from 'ant-design-vue'
import { Card } from 'ant-design-vue'
import { Avatar } from 'ant-design-vue'
import { List } from 'ant-design-vue'
import { Tooltip } from 'ant-design-vue'
import { Comment } from 'ant-design-vue'
import { Tag } from 'ant-design-vue'
import { Carousel } from 'ant-design-vue'
import { Divider } from 'ant-design-vue'
import { Select } from 'ant-design-vue'
import { Button } from 'ant-design-vue'
import { Drawer } from 'ant-design-vue'
import { Form } from 'ant-design-vue'
import { Modal as modal } from 'ant-design-vue'
import { Input } from 'ant-design-vue'
import { Checkbox } from 'ant-design-vue'
import { Table } from 'ant-design-vue'
import { Radio } from 'ant-design-vue'
import { Spin } from 'ant-design-vue'
import { Message as message } from 'ant-design-vue'
import { Pagination } from 'ant-design-vue'

import VeRadar from 'v-charts/lib/radar.common'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

// 自定义的封装, 用作全局
import ajax from '../axios/ajax.js'
import path from '../constant/path.js'
import storage from '../storage/storage.js'

Vue.use(Layout)
Vue.use(Menu)
Vue.use(Breadcrumb)
Vue.use(Row)
Vue.use(Col)
Vue.use(Icon)
Vue.use(Card)
Vue.use(Avatar)
Vue.use(List)
Vue.use(Tooltip)
Vue.use(Comment)
Vue.use(Tag)
Vue.use(Carousel)
Vue.use(Divider)
Vue.use(Select)
Vue.use(Button)
Vue.use(Drawer)
Vue.use(Form)
Vue.use(modal)
Vue.use(Input)
Vue.use(Checkbox)
Vue.use(Table)
Vue.use(Radio)
Vue.use(Spin)
Vue.use(message)
Vue.component(VeRadar.name, VeRadar)
Vue.use(mavonEditor)
Vue.use(Pagination)

Vue.prototype.$message = message
Vue.prototype.$modal = modal
Vue.prototype.$ajax = ajax
Vue.prototype.$path = path
Vue.prototype.$storage = storage
