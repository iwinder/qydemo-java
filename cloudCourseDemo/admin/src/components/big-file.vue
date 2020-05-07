<template>
    <div>
      <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
          <i class="ace-icon fa fa-upload"></i>{{text}}
      </button>
      <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
     
    </div>
</template>
<script>
export default {
    name: 'bug-file',
    props: {
      text: {
        default: '上传文件'
      },
      inputId: {
        default: 'file-upload'
      },
      suffixs: {
        default: []
      },
      afterUpload: {
        type: Function,
        default: null
      },
      use: {
          default: ""
      },
    },
    data: function () {
      return {
        
      }
    },
    methods: {
      /**
      * 点击【上传】
      */
      selectFile () {
        let _this = this;
        $("#" + _this.inputId + "-input").trigger("click");
      },
      /**
       * 上传文件
       */
      uploadFile() {
          let _this = this;
          let formData = new window.FormData();
          let file = _this.$refs.file.files[0];

          // 判断文件格式
          let suffixs = _this.suffixs;
          if(!(!suffixs || JSON.stringify(suffixs) === "{}" || suffixs.length === 0)) {
              let fileName = file.name;
              let suffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length).toLowerCase();
              let validateSuffix = false;
              for(let s of suffixs) {
                  if(s.toLocaleLowerCase() === suffix) {
                      validateSuffix = true;
                      break;
                  }
              }
              if(!validateSuffix) {
                  Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
                  $("#" + _this0.inputId + "-input").val("");
                  return;
              }
          }

          // 文件分片
          let shardSize = 20 * 1024 *1024; // 20M为一个分片
          let shardIndex = 0;   // 分片索引
          let start = shardIndex * shardSize; // 当前分片起始位置
          let end = Math.min(file.size, start + shardSize); // 当前分片结束位置
          let fileShard = file.slice(start, end); // 从文件中截取当前的分片数据
          

          // key:"file" 必须和后端controller中参数名一致
          formData.append("file", fileShard);
          formData.append('use', _this.use);
          Loading.show();
          _this.$ajax.post(process.env.VUE_APP_SERVER + "/file/admin/upload", formData).then((res)=> {
              Loading.hide();
              let resp = res.data;
              console.log("文件上传的结果：", resp);
              _this.afterUpload(resp);
              $("#" + _this.inputId + "-input").val("");
              
          });
      },

      
    }
}
</script>

<style scoped>
 
</style>