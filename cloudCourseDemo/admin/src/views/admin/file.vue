<template>
    <div>
        <p>
           
            <button v-on:click="list()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>
        <!-- ref设置pagination组件别名为 pagination -->
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="6"></pagination>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>相对路径</th>
                <th>文件名</th>
                <th>后缀</th>
                <th>大小</th>
                <th>用途</th>

 
            </tr>
            </thead>

            <tbody>
            <tr v-for="file in files" :key="file.id" >
                <td>{{file.id}}</td>
                <td>{{file.path}}</td>
                <td>{{file.name}}</td>
                <td>{{file.suffix}}</td>
                <td>{{file.size}}</td>
                 <td>{{FILE_USE | optionKV(file.use)}}</td>
            
            </tr> <!--tr结束 -->
            </tbody>
        </table>



    
    </div>

</template>

<script>

    import Pagination from '../../components/pagination';

    export default {
        name: 'file-file',
        components: {Pagination},
        data: function() {
            return {
                file: {},
                files: [],
                FILE_USE:FILE_USE,
            }
        },
        mounted: function() {
            // this.$parent.activeSidebar("sidebar-file-file");
            let _this = this;
            _this.list(1);
        },
        methods: {
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/file/admin/file/list", {
                    page: page,
                    size: _this.$refs.pagination.size // $refs使用组件别名pagination，获取组件里面的变量size
                }).then((response)=>{
                    Loading.hide(_this.$isDebug);
                    console.log("查询文件的结果：", response);
                    let resp = response.data;
                    _this.files = resp.content.list;
                    // 重新渲染分页组件，使其页码样式与查询页数相同
                    _this.$refs.pagination.render(page, resp.content.total);
                });
            }
        }
    }
</script>

