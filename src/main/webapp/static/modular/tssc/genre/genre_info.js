/**
 * 初始化产品类别详情对话框
 */
var GenreInfoDlg = {
    genreInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '工作室名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
GenreInfoDlg.clearData = function() {
    this.genreInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GenreInfoDlg.set = function(key, val) {
    this.genreInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GenreInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GenreInfoDlg.close = function() {
    parent.layer.close(window.parent.Genre.layerIndex);
}

/**
 * 收集数据
 */
GenreInfoDlg.collectData = function() {
    this.set('id').set('name').set('remark');
}

/**
 * 提交添加
 */
GenreInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/genre/add", function(data){
        Feng.success("添加成功!");
        window.parent.Genre.table.refresh();
        GenreInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.genreInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GenreInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/genre/update", function(data){
        Feng.success("修改成功!");
        window.parent.Genre.table.refresh();
        GenreInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.genreInfoData);
    ajax.start();
}

$(function() {

});
