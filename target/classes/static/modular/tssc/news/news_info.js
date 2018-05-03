/**
 * 初始化新闻详情对话框
 */
var NewsInfoDlg = {
    newsInfoData : {}
};

/**
 * 清除数据
 */
NewsInfoDlg.clearData = function() {
    this.newsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsInfoDlg.set = function(key, val) {
    this.newsInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NewsInfoDlg.close = function() {
    parent.layer.close(window.parent.News.layerIndex);
}

/**
 * 收集数据
 */
NewsInfoDlg.collectData = function() {
    var body = um.getContent();
    this.newsInfoData.content = body;
    var image = $("#image1").val() +","+ $("#image2").val() +","+ $("#image3").val();
    this.newsInfoData.image = image;
    this.set('id').set("name").set("time").set("synopsis");
}

/**
 * 提交添加
 */
NewsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/news/add", function(data){
        Feng.success("添加成功!");
        window.parent.location.reload();
        NewsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NewsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/news/update", function(data){
        Feng.success("修改成功!");
        window.parent.location.reload();
        NewsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newsInfoData);
    ajax.start();
}

$(function() {
    // 初始化图片上传
    var url = '/news/upload'

    var image1 = new $WebUpload("image1",url,true);
    image1.init();
    var image2 = new $WebUpload("image2",url,true);
    image2.init();
    var image3 = new $WebUpload("image3",url,true);
    image3.init();
});
