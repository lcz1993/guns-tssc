/**
 * 初始化服务领域详情对话框
 */
var FieldInfoDlg = {
    fieldInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        introduce: {
            validators: {
                notEmpty: {
                    message: '简介不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
FieldInfoDlg.clearData = function() {
    this.fieldInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FieldInfoDlg.set = function(key, val) {
    this.fieldInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FieldInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FieldInfoDlg.close = function() {
    parent.layer.close(window.parent.Field.layerIndex);
}

/**
 * 收集数据
 */
FieldInfoDlg.collectData = function() {
    this.set('id').set('name').set('introduce');
}

/**
 * 提交添加
 */
FieldInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/field/add", function(data){
        Feng.success("添加成功!");
        // window.parent.Field.table.refresh();
        FieldInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    window.parent.location.reload();
    ajax.set(this.fieldInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FieldInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/field/update", function(data){
        Feng.success("修改成功!");
        // window.parent.Field.table.refresh();
        FieldInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    window.parent.location.reload();
    ajax.set(this.fieldInfoData);
    ajax.start();
}

$(function() {

});
