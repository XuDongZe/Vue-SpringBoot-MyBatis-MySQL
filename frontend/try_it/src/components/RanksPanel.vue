<template>
<a-card title="成就排行">
	<div class="personal-order-container" >
		<a class="order" v-if="!isLogin"
      @click="openLoginModal"> 
      登录查看自己排名 
    </a>
		<span class="order" v-else>我的排名: 
			<span style="margin-left: 16px">{{ this.$store.getters.user.rankOrder }}</span>
		</span>
	</div>
  <a-table
  size="small"
  :dataSource="customerList" 
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
		
    <span slot="customer_name" slot-scope="text, record">
      <router-link :to="{path:'/personal/info', query:{id: record.id}}">{{ text }}</router-link>
    </span>

    <a-icon slot="filterIcon" slot-scope="filtered" type='search' :style="{ color: filtered ? '#108ee9' : undefined }" />
  </a-table>
 </a-card>
</template>

<script>

export default {
  name: "RanksPanel",
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
      customerList: [],
      columns : [
        {
          title: "昵称",
          dataIndex: "username",
          key: "username",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customer_name',
          },
          onFilter: (value, record, text) => record.username.toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus()
              },0)
            }
          }
        },
        {
          title: "成就值",
          dataIndex: "achievement",
          key: "achievement",
          scopedSlots: {
            customRender: 'customer_achievement'
          },
          sorter: (a, b) => a.achievement - b.achievement
        },
        {
          title: "排名",
          dataIndex: "rankOrder",
          key: "rankOrder",
          scopedSlots: {
            customRender: 'customer_rank'
          },
        }
      ]
    }
  },

  computed: {
  	isLogin() {
  		return this.$store.getters.isLogin
  	},
  	pagination() {
      return {
        current: 1,
        defaultPageSize: 10,
        change: this.handlePagerChange
      }
    }
  },

  methods: {
  	openLoginModal() {
      this.$store.commit('openLoginModal')
    },

    handleSearch (selectedKeys, confirm) {
      confirm()
      this.searchText = selectedKeys[0]
    },

    handleReset (clearFilters) {
      clearFilters()
      this.searchText = ''
    },

    handlePagerChange (pageNum, pageSize) {
      this.fetch({
        pageNum: pageNum,
        pageSize: pageSize
      })
    },

    fetch(objs = {}) {
      let url = this.$path.CustomerRanks
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
        this.customerList = response.data.list;
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

.personal-order-container {
	margin-bottom: 16px;
}
</style>
