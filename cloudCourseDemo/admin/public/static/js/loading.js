Loding = {
    show: function() {
        $.blockUI({
            message: '<img src="/static/images/loading.gif" />',
            css: {
              padding: "10px",
              left: "50%",
              width: "80px",
              marginLeft: "-40px",
            }
        });
    },
    hide: function(isDeBug) {
        if (isDeBug) {
            // 本地查询速度太快，loading显示一瞬间，故意做个延迟
            setTimeout(function () {
                $.unblockUI();
            }, 500)
        } else {
            $.unblockUI();
        }

    }
}