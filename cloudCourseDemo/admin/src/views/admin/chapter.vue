<template>
    <div>
        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
               <i class="ace-icon fa fa-edit"></i>
                新增
            </button>
            &nbsp;
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
                    <!-- <th class="center">
                        <label class="pos-rel">
                            <input type="checkbox" class="ace" />
                            <span class="lbl"></span>
                        </label>
                    </th> -->
                    <!-- <th class="detail-col">Details</th> -->
                    <th>ID</th>
                    <th>名称</th>
                     <th>课程ID</th>
                    <!-- <th class="hidden-480">Clicks</th> -->
                     <th>操作</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="chapter in chapters" :key="chapter.id" >
                    <td>{{chapter.id}}</td>
                    <td>{{chapter.name}}</td>
                    <td>{{chapter.courseId}}</td>
                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                             <!-- 编辑 -->
                            <button v-on:click="edit(chapter)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>
                            <!-- 删除 -->
                            <button v-on:click="del(chapter.id)" class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                            </button>
                        </div>

                        <div class="hidden-md hidden-lg">
                            <div class="inline pos-rel">
                                <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                </button>

                                <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                    
                                    <li>
                                        <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                            <span class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                            </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                            <span class="red">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </td>
                </tr> <!--tr结束 -->
            </tbody>
        </table>



        <!-- Modal -->
        <div id="form-modal" class="modal fade"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">表单</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label   class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                        <input type="email" v-model="chapter.name" class="form-control" placeholder="名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label   class="col-sm-2 control-label">课程ID</label>
                        <div class="col-sm-10">
                        <input    v-model="chapter.courseId" class="form-control"   placeholder="课程ID">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" v-on:click="save()" class="btn btn-primary">保存</button>
            </div>
            </div>
        </div>
        </div>
    </div>
    
</template>

<script>

import Pagination from '../../components/pagination';

export default {
    name: 'chapter',
    components: {Pagination},
    data: function() {
        return {
            chapter: {},
            chapters: []
        }
    },
    mounted: function() {
        // this.$parent.activeSidebar("sidebar-business-chapter");
        let _this = this;
        _this.list(1);
    },
    methods: {
        /**
         * 点击【新增】
         */
        add() {
            let _this = this;
            _this.chapter = {};
            $("#form-modal").modal("show");
        },
        /**
         * 点击【编辑】
         */
        edit(chapter) {
            let _this = this;
            // _this.chapter = chapter;
            // 复制给新对象，防止修改影响到源对象
            _this.chapter = $.extend({},chapter);
            $("#form-modal").modal("show");
        },
        /**
         * 点击【保存】
         */
        save() {
            let _this = this;
            // 保存校验
            if (!Validator.require(_this.chapter.name, "名称")
                || !Validator.require(_this.chapter.courseId, "课程ID")
                || !Validator.length(_this.chapter.courseId, "课程ID", 1, 8)) {
                return;
            }
            Loding.show();
            _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/chapter/save",  _this.chapter).then((response)=>{
                Loding.hide(_this.$isDebug);
                console.log("保存大章的结果：", response);
                let resp = response.data;
                if(resp.success) {
                    $("#form-modal").modal("hide");
                    _this.list(1);
                    Toast.success("保存成功");
                }else {
                    Toast.error(resp.message);
                }
                
                
            });
        },
       /**
        * 点击【删除】
        */
        del(chapterId) {
            let _this = this;
            Confirm.show('确认删除？',"删除后不可恢复，确认删除？", function(){
                Loding.show();
                _this.$ajax.delete(process.env.VUE_APP_SERVER + "/business/admin/chapter/delete/"+chapterId).then((response)=>{
                Loding.hide(_this.$isDebug);
                console.log("删除大章列表结果：", response);
                let resp = response.data;
                    if (resp.success) {
                        _this.list(1);
                        Toast.success("删除成功");
                        
                    }
                })
            }) 
        },
        /**
         * 列表查询
         */
        list(page) {
            let _this = this;
            _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/chapter/list", {
                page: page,
                size: _this.$refs.pagination.size // $refs使用组件别名pagination，获取组件里面的变量size
            }).then((response)=>{
                console.log("查询大章的结果：", response);
                let resp = response.data;
                _this.chapters = resp.content.list;
                // 重新渲染分页组件，使其页码样式与查询页数相同
                _this.$refs.pagination.render(page, resp.content.total);
            });
        }
    }
}
</script>