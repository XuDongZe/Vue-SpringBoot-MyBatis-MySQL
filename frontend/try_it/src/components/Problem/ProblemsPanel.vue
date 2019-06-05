<template>
<a-card title="题目列表">
  <a-table 
  :dataSource="problemList" 
  :columns="columns" 
  @change="handleTableChange"
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
    <span slot="problem_title" slot-scope="text, record">
      <router-link :to="{path:'/problems/about', query:{id: record.id}}">{{ text }}</router-link>
    </span>
    <span slot="problme_tags" slot-scope="text">
      <a-tag v-for="tag in text" :key="tag.id" color="#87d068">
        <router-link :to="'/problems/tag/' + tag.id">{{ tag.name }}</router-link>
      </a-tag>
    </span>
    <span slot="problem_author" slot-scope="text">
      <router-link :to="'/personal/' + text.id">{{ text.username }}</router-link>
    </span>
    <span slot="problem_difficulty" slot-scope="text">
      <a-tag :color="getColorByDifficulty(text)"> {{ text }} </a-tag>
    </span>
<!--     <span slot="problem_correctRate" slot-scope="text">
      {{ text + "%" }}
    </span> -->
    <a-icon slot="filterIcon" slot-scope="filtered" type='search' :style="{ color: filtered ? '#108ee9' : undefined }" />
  </a-table>
 </a-card>
</template>

<script>
import {getColorByDifficulty} from '../../constant/constant.js'

export default {
  name: "ProblemsPanel",
  data () {
    return {
      pagination: {
        current: 1,
        pageSize: 10,
        defaultPageSize: 10
      },
      getColorByDifficulty: getColorByDifficulty,
      searchText: '',
      searchInput: null,
      problemList: [],
      columns : [
        {
          title: "题号",
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
            customRender: 'problem_title',
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
            customRender: 'problem_author',
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
          title: "知识点",
          dataIndex: "tags",
          key: "tags",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'problme_tags',
          },
          onFilter: (value, record) => {
            let temp = value.toLowerCase()
            for(let item of record.tags) {
              if(item.name.toLowerCase().includes(temp)) {  
                return true
              }
            }
            return false
          },
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus()
              },0)
            }
          }
        },
        {
          title: "难度",
          dataIndex: "difficulty",
          key: "difficulty",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'problem_difficulty',
          },
          onFilter: (value, record) => record.difficulty.toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus()
              },0)
            }
          }
        }
      ]
    }
  },

  created () {
    this.fetch();
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

    handleTableChange (pagination, filters, sorter) {
      // let objs = JSON.stringify(JSON.parse({...filters}) );
      // let objs1 = JSON.stringify(JSON.parse({...sorter}) );
      // console.log(objs, obj2);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        pageSize: pagination.pageSize,
        pageNum: pagination.current,
      });
    },

    fetch(objs = {}) {
      let url = this.$path.Problems
      this.$ajax.get(url, {
        params: {
          pageNum: 1,
          pageSize: this.pagination.defaultPageSize,
          ...objs
        }
      }).then((response) => {
        // 二级属性修改 
        // 天坑
        // https://www.jianshu.com/p/6164d384dce5
        this.problemList = response.data.list;
        const pager = { ...this.pagination};
        pager.total = response.data.total;
        pager.pageSize = response.data.pageSize;
        this.pagination = pager;
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
