/**
 * 初始化人员详情对话框
 */
var PersonnelInfoDlg = {
    personnelInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        engName: {
            validators: {
                notEmpty: {
                    message: '英文名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
PersonnelInfoDlg.clearData = function() {
    this.personnelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PersonnelInfoDlg.set = function(key, val) {
    this.personnelInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PersonnelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PersonnelInfoDlg.close = function() {
    parent.layer.close(window.parent.Personnel.layerIndex);
}

/**
 * 收集数据
 */
PersonnelInfoDlg.collectData = function() {
    this.set('id').set("name").set("engName").set("introduce").set("position").set("image");
}

/**
 * 提交添加
 */
PersonnelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/personnel/add", function(data){
        Feng.success("添加成功!");
        window.parent.location.reload();
        // window.parent.Personnel.table.refresh();
        PersonnelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.personnelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PersonnelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/personnel/update", function(data){
        Feng.success("修改成功!");
        // window.parent.Personnel.table.refresh();
        window.parent.location.reload();
        PersonnelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.personnelInfoData);
    ajax.start();
}

$(function() {
    // 初始化图片上传
    var url = '/personnel/upload'

    var image = new $WebUpload("image",url,true);
    image.init();
});
