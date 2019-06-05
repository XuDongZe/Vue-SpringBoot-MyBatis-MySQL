<template>
<a-card title="题目列表">
  <a-table 
  :dataSource="articleList" 
  :columns="columns"
  :pagination="pagination"
  :rowKey="(record) => record.id">
    <div slot="filterDropdown" slot-scope="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }" class='custom-filter-dropdown'>
      <a-input
        v-ant-ref="c => searchInput = c"
        :placeholder="`Search ${column.dataIndex}`"
        :value="selectedKeys[0]"
        @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
        @pressEnter="() => handleSearch(selectedKeys, confirm)"
        style="width: 188px; margin-bottom: 8px; display: block;"
      />
      <a-button
        type='primary'
        @click="() => handleSearch(selectedKeys, confirm)"
        icon="search"
        size="small"
        style="width: 90px; margin-right: 8px"
      >Search</a-button>
      <a-button
        @click="() => handleReset(clearFilters)"
        size="small"
        style="width: 90px"
      >Reset</a-button>
    </div>
    <span slot="article_title" slot-scope="text, record">
      <router-link :to="{path:'/articles/about', query:{id: record.id}}">{{ text }}</router-link>
    </span>
    <span slot="article_author" slot-scope="text">
      <router-link :to="'/personal/' + text.id">{{ text.username }}</router-link>
    </span>
    <a-icon slot="filterIcon" slot-scope="filtered" type='search' :style="{ color: filtered ? '#108ee9' : undefined }" />
  </a-table>
 </a-card>
</template>

<script>
// import {getColorByDifficulty} from '../../constant/constant.js'

export default {
  name: "ArticlesPanel",
  created () {
    this.fetch()
  },
  computed: {
    pagination() {
      return {
        current: 1,
        defaultPageSize: 10,
        change: this.handlePagerChange
      }
    }
  },

  data () {
    return {
      searchText: '',
      searchInput: null,
      articleList: [],
      columns : [
        {
          title: "编号",
          dataIndex: "id",
          key: "id",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon'
          },
          onFilter: (value, record) =>  record.id == value,
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus()
              },0)
            }
          }
        },
        {
          title: "标题",
          dataIndex: "title",
          key: "title",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'article_title',
          },
          onFilter: (value, record) => record.title.toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus()
              },0)
            }
          }
        },
        {
          title: "作者",
          dataIndex: "author",
          key: "author",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'article_author',
          },
          onFilter: (value, record) => record.author.username.toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus()
              },0)
            }
          }
        },
        {
          title: "阅读数",
          dataIndex: "readers",
          key: "readers"
        }
      ]
    }
  },
  methods: {
    handleSearch (selectedKeys, confirm) {
      confirm()
      this.searchText = selectedKeys[0]
    },

    handleReset (clearFilters) {
      clearFilters()
      this.searchText = ''
    },

    handlePagerChange (page, pageSize) {
      this.fetch({
        pageNum: page,
        pageSize: pageSize
      })
    },

    fetch(objs = {}) {
      let url = this.$path.Articles
      this.$ajax.get(url, {
        params: {
          pageNum: 1,
          pageSize: this.pagination.defaultPageSize,
          ...objs
        }
      })
      .then((response) => {
        this.pagination.total = response.data.total;
        this.pagination.pageSize = response.data.pageSize;
        this.articleList = response.data.list;
      })
    }
  }
}

</script>
<style scoped>
.custom-filter-dropdown {
  padding: 8px;
  border-radius: 4px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .15);
}

.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}
</style>
