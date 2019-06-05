<template>
<a-card title="题目列表">
  <a-table :dataSource="SolutionList" :columns="columns" rowKey="id">
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
   		<router-link :to="'/problems/title/' + record.title">{{ text }}</router-link>
   	</span>
   	<span slot="problem_author" slot-scope="text, record">
   		<router-link :to="'/personal/author/' + record.author">{{ text }}</router-link>
   	</span>
   	<span slot="problme_tags" slot-scope="text">
   		<a-tag v-for="tag in text" :key="tag" color="#87d068">
   			<router-link :to="'/problems/tag/' + tag">{{ tag }}</router-link>
   		</a-tag>
   	</span>
    <span slot="problem_difficulty" slot-scope="text">
      <a-tag :color="getColorByDifficulty(text)"> {{ text }} </a-tag>
    </span>
    <a-icon slot="filterIcon" slot-scope="filtered" type='search' :style="{ color: filtered ? '#108ee9' : undefined }" />
  </a-table>
 </a-card>
</template>

<script>

export default {
	name: "ProblemsPanel",
	created	() {
		this.$ajax.get('/api/get/problems')
		.then((res) => {
			this.SolutionList = res.data.data;
		}, (err) => {
			console.log(err)
		})
	},
  data () {
    return {
      searchText: '',
      searchInput: null,
    	SolutionList: [],
    	columns : [
    		{
    			title: "题号",
    			dataIndex: "id",
    			key: "id",
    			scopedSlots: {
    			  filterDropdown: 'filterDropdown',
    			  filterIcon: 'filterIcon',
    			  customRender: 'id',
    			},
    			onFilter: (value, record) => record.id.toLowerCase().includes(value.toLowerCase()),
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
    			onFilter: (value, record) => record.author.toLowerCase().includes(value.toLowerCase()),
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
    					if(item.toLowerCase().includes(temp)) {  
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
        },
        {
          title: "阅读",
          dataIndex: "reads",
          key: "reads"
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
    getColorByDifficulty(difficulty) {
      const EASY = "#87d068"
      const NORMAL = "#f0ad4e"
      const HARD = "#d9534f"
      let str = difficulty.toLowerCase()
      switch(str) {
        case "简单":
          return EASY;
          break;
        case "中等":
          return NORMAL;
          break;
        case "困难":
          return HARD;
          break;
        default:
          return NORMAL;
          break; 
      }
    }
  },
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
