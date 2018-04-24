/**
 * 初始化服务预约详情对话框
 */
var BespeakInfoDlg = {
    bespeakInfoData : {}
};

/**
 * 清除数据
 */
BespeakInfoDlg.clearData = function() {
    this.bespeakInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BespeakInfoDlg.set = function(key, val) {
    this.bespeakInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BespeakInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BespeakInfoDlg.close = function() {
    parent.layer.close(window.parent.Bespeak.layerIndex);
}

/**
 * 收集数据
 */
BespeakInfoDlg.collectData = function() {
    var genre = '';
    var service = $("input[name='service']:checked").each(function(j) {
        if (j >= 0) {
            genre += $(this).val() + ","
        }
    });
    this.bespeakInfoData.service = genre;
    this.set('id').set("name").set("title").set("email").set("tel").set("companyName").set("companyWeb").set("budget").set("remark");
}

/**
 * 提交添加
 */
BespeakInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bespeak/add", function(data){
        Feng.success("添加成功!");
        window.parent.Bespeak.table.refresh();
        BespeakInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bespeakInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BespeakInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bespeak/update", function(data){
        Feng.success("修改成功!");
        window.parent.Bespeak.table.refresh();
        BespeakInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bespeakInfoData);
    ajax.start();
}

$(function() {

});
